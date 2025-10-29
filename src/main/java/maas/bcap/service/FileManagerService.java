package maas.bcap.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.FileInSub1Vo;
import maas.bcap.dto.FileInVo;
import maas.bcap.dto.FileOutSub1Vo;
import maas.bcap.dto.FileOutVo;
import maas.bcap.dto.FileUploadInDto;
import maas.bcap.dto.UserInfoFileManagerDto;

@Service
public class FileManagerService {
    private static final Logger log = LogManager.getLogger(FileManagerService.class);

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Value("${file.ext.filter}")
    private String fileExtFilter;

    @Autowired
    private FileUploadService fileUploadService;

    public int upload(
            HttpServletRequest request,
            AuthInfoDto authInfoDto,
            FileUploadInDto inDto,
            List<MultipartFile> files) throws Exception {
        log.info("FileUploadService.upload");

        /// Check Content Type
        Optional<String> contentType = Optional.ofNullable(request.getContentType());
        log.info("Content-Type {}", contentType.get());
        if (contentType.isEmpty()
                || (contentType.isPresent() && !contentType.get().startsWith(MediaType.MULTIPART_FORM_DATA_VALUE))) {
            log.error("Content Type is not {}", MediaType.MULTIPART_FORM_DATA_VALUE);
            throw new Exception("Content Type is not " + MediaType.MULTIPART_FORM_DATA_VALUE);
        }

        if (files == null || files.isEmpty()) {
            log.error("There is no files uploaded");
            throw new Exception("There is no files uploaded");
        }

        String userIp = getClientIpAddress(request);
        final UserInfoFileManagerDto userInfoFileManagerDto = UserInfoFileManagerDto.builder()
                .screenId(inDto.getScreenId())
                .userIp(userIp)
                .build();
        log.info(userInfoFileManagerDto);

        if (inDto.getFileDiv().equals("") || inDto.getFileDiv() == null) {
            log.error("Path Set Error");
            throw new Exception("Path Set Error");
        }

        if (inDto.getAttachFileId() != null && !inDto.getAttachFileId().equals(""))
            return updateFiles(authInfoDto, inDto, files, userInfoFileManagerDto);
        else
            return registerFiles(authInfoDto, inDto, files, userInfoFileManagerDto);

    }

    private int registerFiles(
            AuthInfoDto authInfoDto,
            FileUploadInDto inDto,
            List<MultipartFile> files,
            UserInfoFileManagerDto userInfoFileManagerDto) throws Exception {
        log.info("FileManagerService.registerFiles");

        files = files.stream().filter(file -> {
            String originalFileName = file.getOriginalFilename();
            return originalFileName != null && !originalFileName.isBlank();
        }).toList();
        log.info(files);

        String filePath = "";

        List<FileInSub1Vo> inSub1Vos = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("");

            if (!checkFileExtension(file)) {
                log.error("File Extention Error");
                throw new Exception("File Extention Error");
            }

            /// File Path
            if (inDto.getExtraPath() == null || inDto.getExtraPath().equals(""))
                filePath = fileUploadPath + File.separator + inDto.getFileDiv();
            else
                filePath = fileUploadPath + File.separator + inDto.getFileDiv() + inDto.getExtraPath();

            FileInSub1Vo inSub1Vo = FileInSub1Vo.builder()
                    .file_nm(originalFileName)
                    .upl_file_size(file.getSize())
                    .file_path(filePath)
                    .inp_pgm_id(inDto.getScreenId())
                    .inp_usr_id(authInfoDto.getUserId())
                    .build();
            inSub1Vos.add(inSub1Vo);
        }
        log.info(inSub1Vos);

        FileInVo inVo = FileInVo.builder()
                .attach_file_clcd(inDto.getFileDiv())
                .attach_file_expl(inDto.getFileDesc())
                .upd_yn("N")
                .sub1Vos(inSub1Vos)
                .build();

        log.info(inVo);

        // Save Files Info into DevonC
        FileOutVo outVo = fileUploadService.saveFilesToDevonC(authInfoDto, inVo,
        userInfoFileManagerDto);

        // /// TODO Dummy
        // List<FileOutSub1Vo> outSub1Vos = inSub1Vos.stream().map(inSub1Vo -> FileOutSub1Vo.builder()
        //         .file_nm(inSub1Vo.file_nm)
        //         .upl_file_size(inSub1Vo.upl_file_size)
        //         .upl_file_nm(inSub1Vo.file_nm)
        //         .file_path(inSub1Vo.file_path)
        //         .inp_pgm_id(inSub1Vo.inp_pgm_id)
        //         .inp_usr_id(inSub1Vo.inp_usr_id)
        //         .build()).collect(Collectors.toList());
        // /// TODO Dummy
        // FileOutVo outVo = FileOutVo.builder()
        //         .attach_file_clcd(inDto.getFileDiv())
        //         .attach_file_expl(inDto.getFileDesc())
        //         .upd_yn("N")
        //         .sub1Vos(outSub1Vos)
        //         .build();
        // log.info(outSub1Vos);
        // log.info(outVo);

        /// Store / Save files to Disk
        saveToDisk(outVo, files, filePath);
        return 1;
    }

    private int updateFiles(
            AuthInfoDto authInfoDto,
            FileUploadInDto inDto,
            List<MultipartFile> files,
            UserInfoFileManagerDto userInfoFileManagerDto) throws Exception {
        FileInVo inVo = FileInVo.builder()
                .attach_file_id(inDto.getAttachFileId())
                .attach_file_clcd(inDto.getFileDiv())
                .build();

        FileOutVo outVo = fileUploadService.selectFileList(authInfoDto, inVo, userInfoFileManagerDto);
        List<FileOutSub1Vo> outSub1Vos = outVo.getSub1Vos();

        String filePath = "";
        if (outSub1Vos != null && !outSub1Vos.isEmpty()) {
            filePath = outSub1Vos.get(0).getFile_path();
        } else {
            if (inDto.getExtraPath() == null || inDto.getExtraPath().equals(""))
                filePath = fileUploadPath + File.separator + inDto.getFileDiv();
            else
                filePath = fileUploadPath + File.separator + inDto.getFileDiv() + inDto.getExtraPath();
        }

        /// Delete Target Exists
        if (inDto.getAttachFileSeqNo() != null && !inDto.getAttachFileSeqNo().isEmpty()) {
            deleteTargetFile(authInfoDto, inDto, outVo, filePath);
        }
        addTargetFile(authInfoDto, inDto, filePath, files);
        return 0;
    }

    private void deleteTargetFile(AuthInfoDto authInfoDto, FileUploadInDto inDto, FileOutVo outVo, String filePath) {
        List<FileInSub1Vo> inSub1Vos = new ArrayList<>();

        for (FileOutSub1Vo outSub1Vo : outVo.getSub1Vos()) {
            int fileSeqNo = Integer.parseInt(outSub1Vo.getAttach_file_seq_no());

            for (String deleteSeq : inDto.getAttachFileSeqNo()) {
                if (Integer.parseInt(deleteSeq) != fileSeqNo)
                    continue;
                FileInSub1Vo inSub1Vo = FileInSub1Vo.builder()
                        .attach_file_id(outSub1Vo.getAttach_file_id())
                        .attach_file_seq_no(outSub1Vo.getAttach_file_seq_no())
                        .del_yn("Y")
                        .chng_usr_id(authInfoDto.getUserId())
                        .chng_pgm_id(inDto.getScreenId())
                        .build();
                inSub1Vos.add(inSub1Vo);
                break;
            }
        }
    }

    private void addTargetFile(
            AuthInfoDto authInfoDto,
            FileUploadInDto inDto,
            String filePath,
            List<MultipartFile> files) throws Exception {
        List<FileInSub1Vo> inSub1Vos = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("");

            if (!checkFileExtension(file)) {
                log.error("File Extention Error");
                throw new Exception("File Extention Error");
            }

            FileInSub1Vo inSub1Vo = FileInSub1Vo.builder()
                    .del_yn("N")
                    .file_nm(originalFileName)
                    .upl_file_size(file.getSize())
                    .file_path(filePath)
                    .inp_pgm_id(inDto.getScreenId())
                    .inp_usr_id(authInfoDto.getUserId())
                    .build();

            inSub1Vos.add(inSub1Vo);
        }
    }

    private void saveToDisk(FileOutVo outVo, List<MultipartFile> files, String filePath)
            throws IllegalStateException, IOException {
        log.info("FileManagerService.saveToDisk");
        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename();

            List<FileOutSub1Vo> outSub1Vos = outVo.getSub1Vos();
            for (FileOutSub1Vo outSub1Vo : outSub1Vos) {
                if (outSub1Vo.getFile_nm() == null || !outSub1Vo.getFile_nm().equals(originalFileName))
                    continue;

                String fileSaveName = outSub1Vo.getUpl_file_nm();
                File saveFolder = new File(filePath);

                if (!saveFolder.exists() || saveFolder.isFile())
                    saveFolder.mkdirs();

                String saveFile = saveFolder + File.separator + fileSaveName;
                file.transferTo(new File(saveFile));
                break;
            }
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP-ClIENT-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private boolean checkFileExtension(MultipartFile file) throws Exception {
        log.info("FileUploadService.checkFileExtension");
        String originFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("");
        log.info("originFileName : [{}]", originFileName);
        for (String allowExt : fileExtFilter.split(",")) {
            log.info(allowExt);
            if (originFileName.endsWith(allowExt))
                return true;
        }

        log.error("BAD Extension file upload!");
        throw new IOException("BAD Extension file upload!");
    }
}
