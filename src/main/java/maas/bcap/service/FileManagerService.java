package maas.bcap.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import maas.bcap.dto.FileInSub1Vo;
import maas.bcap.dto.FileInVo;
import maas.bcap.dto.FileOutSub1Vo;
import maas.bcap.dto.FileOutVo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class FileManagerService {
    private static final Logger log = LogManager.getLogger(FileManagerService.class);

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Value("${file.ext.filter}")
    private String fileExtFilter;

    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("FileManagerService.upload");

        Optional<String> checkMultiPartOptional = Optional.ofNullable(request.getContentType());
        Optional<MultipartHttpServletRequest> multipartRequest = Optional.empty();
        Map<String, MultipartFile> multipartFileMap = new HashMap<String, MultipartFile>();
        List<MultipartFile> multipartFileList = new ArrayList<>();

        if (checkMultiPartOptional.isPresent()
                && checkMultiPartOptional.get().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            multipartRequest = Optional.of((MultipartHttpServletRequest) request);
            multipartFileMap = multipartRequest.get().getFileMap();
        }

        for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
            multipartFileList.add(entry.getValue());
        }

        log.info(checkMultiPartOptional.get());
        // log.info(multipartRequest.get());
        log.info(multipartFileMap);
        log.info(multipartFileList);

        /// DTO
        String fileDiv = validateFilePath(Optional.ofNullable(request.getParameter("fileDiv")).orElse(""));
        String fileDesc = Optional.ofNullable(request.getParameter("fileDesc")).orElse("");
        String extraPath = validateFilePath(Optional.ofNullable(request.getParameter("extraPath")).orElse(""));
        String attachFileId = Optional.ofNullable(request.getParameter("attachFileId")).orElse("");
        String[] deleteTargetSeqInfo = request.getParameterValues("attachFileSeqNo");

        String usrId = request.getParameter("usrId");
        String scrId = request.getParameter("scrId");
        String usrIp = getClientIpAddress(request);

        log.info(fileDiv);
        log.info(fileDesc);
        log.info(extraPath);
        log.info(attachFileId);
        log.info(deleteTargetSeqInfo);
        log.info(usrId);
        log.info(scrId);
        log.info(usrIp);

        Boolean isNewFiles = true;
        String errorMessage = "";
        String filePath = "";

        if (fileDiv.equals("")) {
            errorMessage = "Path Set Error";
            // Throw
        }

        if (!attachFileId.equals("")) {
            isNewFiles = false;
        }

        /// Register New Files
        if (1 == 1) {

            List<FileInSub1Vo> inSub1Vos = new ArrayList<>();

            // TODO Dummy nanti di hapus
            List<FileOutSub1Vo> outSub1Vos = new ArrayList<>();

            for (MultipartFile file : multipartFileList) {
                String originFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("");
                if (originFileName.equals(""))
                    continue;

                /// Check Extenion
                if (!checkFileExtension(file)) {
                    errorMessage = "File Extention Error";
                    // throw
                }

                if (extraPath.equals(""))
                    filePath = fileUploadPath + File.separator + fileDiv;
                else
                    filePath = fileUploadPath + File.separator + fileDiv + extraPath;

                FileInSub1Vo inSub1Vo = FileInSub1Vo.builder()
                        .file_nm(originFileName)
                        .upl_file_size(file.getSize())
                        .file_path(filePath)
                        .inp_pgm_id(scrId)
                        .inp_usr_id(usrIp)
                        .build();
                inSub1Vos.add(inSub1Vo);

                // TODO Dummy nanti di hapus
                FileOutSub1Vo outSub1Vo = FileOutSub1Vo.builder()
                        .file_nm(originFileName)
                        .upl_file_size(file.getSize())
                        .file_path(filePath)
                        .upl_file_nm(originFileName)
                        .inp_pgm_id(scrId)
                        .inp_usr_id(usrIp)
                        .build();
                outSub1Vos.add(outSub1Vo);
            }

            FileInVo fileInVo = FileInVo.builder()
                    .attach_file_clcd(fileDiv)
                    .attach_file_expl(fileDesc)
                    .upd_yn("N")
                    .sub1Vos(inSub1Vos)
                    .build();

            /// TODO Save Files Info into DevonC

            /// TODO Dummy
            FileOutVo fileOutVo = FileOutVo.builder()
                    .attach_file_clcd(fileDiv)
                    .attach_file_expl(fileDesc)
                    .upd_yn("N")
                    .sub1Vos(outSub1Vos)
                    .build();

            /// Save Files to Disk
            saveToDisk(fileOutVo, multipartFileList, filePath);
        }
    }

    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("FileManagerService.download");

        String filePath = "D:/bcap/aaa/535999.png";

        File file = new File(filePath);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File Not Found");
            return;
        }

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setContentLengthLong(file.length());

        try {
            BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[10240];
            int bytesRead;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();

        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error during file download", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error downloading file");
        }
    }

    private void saveToDisk(FileOutVo fileOutVo, List<MultipartFile> multipartFileList, String filePath)
            throws Exception {
        for (MultipartFile file : multipartFileList) {
            String originFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("");

            List<FileOutSub1Vo> outSub1Vos = fileOutVo.getSub1Vos();

            for (FileOutSub1Vo outSub1Vo : outSub1Vos) {
                if (outSub1Vo.getFile_nm() != null && outSub1Vo.getFile_nm().equals(originFileName)) {
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
    }

    private String validateFilePath(String filePath) {
        String result = null;

        if (filePath != null) {
            result = filePath.replaceAll("../", "");
        }

        return result;
    }

    private boolean checkFileExtension(MultipartFile file) throws Exception {
        boolean rslt = false;

        String originFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("");
        String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1, originFileName.length());
        String allowList = fileExtFilter;
        StringTokenizer tk = new StringTokenizer(allowList, ",");
        Map<String, String> allowMap = new HashMap<String, String>();

        while (tk.hasMoreTokens()) {
            String tmpExt = tk.nextToken();
            allowMap.put(tmpExt, tmpExt);
        }

        String chkRslt = allowMap.get(ext.toLowerCase());

        if (chkRslt == null) {
            throw new IOException("BAD Extension file upload!");
        } else
            rslt = true;
        return rslt;
    }

    public String getClientIpAddress(HttpServletRequest request) {
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
}
