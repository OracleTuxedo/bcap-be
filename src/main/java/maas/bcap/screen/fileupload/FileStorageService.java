package maas.bcap.screen.fileupload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    public static final String STORAGE_DIRECTORY = "C:\\Storage";

    public void saveFile(MultipartFile fileToSave) throws IOException {
        if (fileToSave == null) {
            throw new NullPointerException("fileToSave is null");
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + fileToSave.getOriginalFilename());
        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Unsupported filename!");
        }
        Files.copy(fileToSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public File getDownloadFile(String fileName) throws Exception {
        if (fileName == null) {
            throw new NullPointerException("fileName is null");
        }
        var fileToDownload = new File(STORAGE_DIRECTORY + File.separator + fileName);
        if (!Objects.equals(fileToDownload.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Unsupported filename!");
        }
        if (!fileToDownload.exists()) {
            throw new FileNotFoundException("No file named: " + fileName);
        }
        return fileToDownload;
    }


}
//
//
//package maas.bcap.screen.fileupload;
//import maas.bcap.screen.fileupload.vo.FileManagerInVo;
//import maas.bcap.screen.fileupload.vo.FileManagerOutVo;
//import mti.com.telegram.vo.TelegramUserDataOutput;
//import mti.com.telegram.vo.TelegramMessage;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.UnknownHostException;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//public class FileStorageService {
//
//    private static final Logger logger = LogManager.getLogger(FileStorageService.class);
//    public static final String STORAGE_DIRECTORY = "C:\\Storage";
//
//    private final static String MSGNORMAL = "N";
//    private final static String RETURN_NULL = "No Data Returned";
//    private final static String ERRORCD = "Error Occurred - Service failed";
//    private final static String TPERR = "Getting Abnormal Message";
//
//    public FileManagerInVo saveFileData(FileManagerInVo fInVO, Map<String, Object> usrInfo, MultipartFile fileToSave) throws Exception {
//        fInVO.setReq_clcd("U1");
//        FileManagerOutVo outputVO = new FileManagerOutVo();
//
//        // Save the file to the storage
//        if (fileToSave == null) {
//            throw new NullPointerException("fileToSave is null");
//        }
//        var targetFile = new File(STORAGE_DIRECTORY + File.separator + fileToSave.getOriginalFilename());
//        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
//            throw new SecurityException("Unsupported filename!");
//        }
//        Files.copy(fileToSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//        // Process with Telegram interface
//        TelegramUserDataOutput resultMsg = interfaceTuxedo(fInVO, outputVO, usrInfo);
//        TelegramMessage message = resultMsg.getMessage();
//        String msg = message.getKind();
//
//        if (msg == null) {
//            throw new Exception(RETURN_NULL);
//        } else if (MSGNORMAL.equals(msg)) {
//            outputVO = (FileManagerOutVo) resultMsg.getOutput();
//            if (!"00".equals(outputVO.getRson_cd())) {
//                throw new Exception(ERRORCD);
//            }
//        } else {
//            throw new Exception(TPERR);
//        }
//
//        return outputVO;
//    }
//
//    public FileManagerOutVo selectFileInfo(FileManagerInVo fInVO, Map<String, Object> usrInfo) throws Exception {
//        fInVO.setReq_clcd("S3");
//        FileManagerOutVo outputVO = new FileManagerOutVo();
//
//        // Retrieve file information
//        TelegramUserDataOutput resultMsg = interfaceTuxedo(fInVO, outputVO, usrInfo);
//        TelegramMessage message = resultMsg.getMessage();
//        String msg = message.getKind();
//
//        if (msg == null) {
//            throw new Exception(RETURN_NULL);
//        } else if (MSGNORMAL.equals(msg)) {
//            outputVO = (FileManagerOutVo) resultMsg.getOutput();
//            if (!"00".equals(outputVO.getRson_cd())) {
//                throw new Exception(ERRORCD);
//            }
//            // Retrieve the file from storage
//            var fileToDownload = new File(STORAGE_DIRECTORY + File.separator + outputVO.getAttach_file_id());
//            if (!Objects.equals(fileToDownload.getParent(), STORAGE_DIRECTORY)) {
//                throw new SecurityException("Unsupported filename!");
//            }
//            if (!fileToDownload.exists()) {
//                throw new FileNotFoundException("No file named: " + outputVO.getAttach_file_id());
//            }
//            outputVO.setFilePath(fileToDownload.getAbsolutePath()); // Assume a field in FileInfoOutVO to store file path
//        } else {
//            throw new Exception(TPERR);
//        }
//
//        return outputVO;
//    }
//
//    private TelegramOutputUserData interfaceTuxedo(FileInfoInVO fInVO, FileInfoOutVO fOutVO, Map<String, Object> usrInfo) throws UnknownHostException {
//        logger.info("Start Telegram Service (SAZ06F010U) : File Upload Manager");
//
//        TelegramInputUserData userData = new TelegramInputUserData();
//        userData.setTx_code("SAZ06F010U");
//        userData.setClient_ip_no((String) usrInfo.get("usrIp"));
//        userData.setClient_mac("");
//        userData.setScrn_id((String) usrInfo.get("scrId"));
//        userData.setOp_id((String) usrInfo.get("usrId"));
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        TelegramOutputUserData resultMsg = null;
//        try {
//            resultMsg = (TelegramOutputUserData) InterfaceTelegram.interfaceTuxedo(userData, fInVO, fOutVO);
//            TelegramMessage message = resultMsg.getMessage();
//            logger.info("Message Info : " + message.toString());
//        } catch (Exception e) {
//            logger.error("Error in Telegram interface", e);
//        }
//
//        return resultMsg;
//    }
//}
