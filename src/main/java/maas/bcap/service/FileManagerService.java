package maas.bcap.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.FileDownloadInDto;
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

    public int download(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthInfoDto authInfoDto,
            FileDownloadInDto inDto) throws Exception {
        log.info("FileManagerService.download");

        // TODO Dummy — replace with real DevonC file lookup
        File file = new File(fileUploadPath + File.separator + "div1" + File.separator + "v2.jpg");
        if (!file.exists()) {
            log.error("File not found");
            return 0;
        }

        try {
            setDisposition("v2.jpg", request, response);

            ServletOutputStream outStream = response.getOutputStream();
            BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));

            int cnt = 0;
            byte[] buffer = new byte[1024];
            while ((cnt = inStream.read(buffer, 0, 1024)) != -1) {
                outStream.write(buffer, 0, cnt);
            }

            inStream.close();
        } catch (Exception e) {
            log.error("File download error", e);
        }

        return -1;
    }

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

        if (inDto.getFileDiv() == null || inDto.getFileDiv().equals("")) {
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
                filePath = buildSafeFilePath(fileUploadPath, inDto.getFileDiv(), inDto.getExtraPath());

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

        // TODO Dummy — replace with: fileUploadService.saveFilesToDevonC(authInfoDto, inVo, userInfoFileManagerDto)
        List<FileOutSub1Vo> outSub1Vos = inSub1Vos.stream().map(inSub1Vo -> FileOutSub1Vo.builder()
                .file_nm(inSub1Vo.file_nm)
                .upl_file_size(inSub1Vo.upl_file_size)
                .upl_file_nm(inSub1Vo.file_nm)
                .file_path(inSub1Vo.file_path)
                .inp_pgm_id(inSub1Vo.inp_pgm_id)
                .inp_usr_id(inSub1Vo.inp_usr_id)
                .build()).collect(Collectors.toList());
        FileOutVo outVo = FileOutVo.builder()
                .attach_file_clcd(inDto.getFileDiv())
                .attach_file_expl(inDto.getFileDesc())
                .upd_yn("N")
                .sub1Vos(outSub1Vos)
                .build();
        log.info(outSub1Vos);
        log.info(outVo);

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

        FileOutVo outVo = fileUploadService.selectFileInfoList(authInfoDto, inVo, userInfoFileManagerDto);
        List<FileOutSub1Vo> outSub1Vos = outVo.getSub1Vos();

        String filePath = "";
        if (outSub1Vos != null && !outSub1Vos.isEmpty()) {
            filePath = outSub1Vos.get(0).getFile_path();
        } else {
            if (inDto.getExtraPath() == null || inDto.getExtraPath().equals(""))
                filePath = fileUploadPath + File.separator + inDto.getFileDiv();
            else
                filePath = buildSafeFilePath(fileUploadPath, inDto.getFileDiv(), inDto.getExtraPath());
        }

        List<FileInSub1Vo> inSub1Vos = new ArrayList<>();

        /// Delete Target Exists
        if (inDto.getAttachFileSeqNo() != null && !inDto.getAttachFileSeqNo().isEmpty()) {
            inSub1Vos.addAll(deleteTargetFile(authInfoDto, inDto, outVo, filePath));
        }
        inSub1Vos.addAll(addTargetFile(authInfoDto, inDto, filePath, files));

        inVo = FileInVo.builder()
                .attach_file_id(inDto.getAttachFileId())
                .upd_yn("Y")
                .sub1Vos(inSub1Vos)
                .build();

        // Save Files Info into DevonC
        outVo = fileUploadService.saveFilesToDevonC(authInfoDto, inVo, userInfoFileManagerDto);

        /// Store / Save files to Disk
        saveToDisk(outVo, files, filePath);

        /// Delete Files from Disk
        deleteFromDisk(outVo, filePath);

        return 0;
    }

    private List<FileInSub1Vo> deleteTargetFile(
            AuthInfoDto authInfoDto,
            FileUploadInDto inDto,
            FileOutVo outVo,
            String filePath) {
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
        return inSub1Vos;
    }

    private List<FileInSub1Vo> addTargetFile(
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
        return inSub1Vos;
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

    private void deleteFromDisk(FileOutVo outVo, String filePath) {
        log.info("FileManagerService.deleteFromDisk");
        for (FileOutSub1Vo outSub1Vo : outVo.getSub1Vos()) {
            String delYn = outSub1Vo.getDel_yn();
            if (delYn == null || !delYn.equals("Y"))
                continue;

            String fileSaveName = outSub1Vo.getUpl_file_nm();
            String savedFile = filePath + File.separator + fileSaveName;

            File file = new File(savedFile);
            if (file.exists())
                file.delete();
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

        // Sanitize filename - strip path components to prevent path traversal
        originFileName = new File(originFileName).getName();

        for (String allowExt : fileExtFilter.split(",")) {
            log.info(allowExt);
            if (originFileName.toLowerCase().endsWith("." + allowExt.trim().toLowerCase()))
                return true;
        }

        log.error("BAD Extension file upload!");
        throw new IOException("BAD Extension file upload!");
    }

    private String buildSafeFilePath(String basePath, String fileDiv, String extraPath) throws Exception {
        Path base = Path.of(basePath).normalize().toAbsolutePath();
        Path resolved = base.resolve(fileDiv + extraPath).normalize().toAbsolutePath();
        if (!resolved.startsWith(base)) {
            log.error("Path traversal attempt detected: extraPath [{}]", extraPath);
            throw new Exception("Invalid file path");
        }
        return resolved.toString();
    }

    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header == null)
            return "Unknown";

        if (header.contains("MSIE")) {
            return "MSIE";
        } else if (header.contains("Trident")) { // IE 11
            return "IE11";
        } else if (header.contains("Edg/")) { // ✅ Microsoft Edge (Chromium-based)
            return "Edge";
        } else if (header.contains("Edge/")) { // ✅ Legacy Edge (pre-Chromium)
            return "Edge";
        } else if (header.contains("Chrome")) {
            return "Chrome";
        } else if (header.contains("Safari")) {
            // Safari must come after Chrome because Chrome UA also contains "Safari"
            return "Safari";
        } else if (header.contains("Opera") || header.contains("OPR/")) {
            return "Opera";
        } else if (header.contains("Firefox")) {
            return "Firefox";
        }

        return "Unknown";
    }

    /**
     * Sets the Content-Disposition header for file download,
     * ensuring proper filename encoding across browsers.
     */
    private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String browser = getBrowser(request);
        String encodedFilename;

        switch (browser) {
            case "MSIE":
            case "Trident":
            case "Edge":
                encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
                break;
            case "Firefox":
            case "Opera":
                encodedFilename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
                break;
            case "Chrome":
            case "Safari":
                StringBuilder sb = new StringBuilder();
                for (char c : filename.toCharArray()) {
                    if (c > '~') {
                        sb.append(URLEncoder.encode(String.valueOf(c), "UTF-8"));
                    } else {
                        sb.append(c);
                    }
                }
                encodedFilename = sb.toString();
                break;
            default:
                encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
                break;
        }

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=\"" + encodedFilename + "\"; filename*=UTF-8''"
                        + URLEncoder.encode(filename, "UTF-8"));
        response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");

        // ✅ The critical fix:
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition, Content-Type");
    }

}
