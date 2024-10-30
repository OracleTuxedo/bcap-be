package maas.bcap.screen.fileupload;
import maas.bcap.screen.fileupload.vo.FileManagerInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class FileManagerController {

    @Autowired
    private FileStorageService fileStorageService;
    private static final Logger log = Logger.getLogger(FileManagerController.class.getName());

    @PostMapping("/upload-file")
    public boolean uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileStorageService.saveFile(file);
            return true;
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception during upload", e);
        }
        return false;
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String filename) {
        log.log(Level.INFO, "[NORMAL] Download with /download");
        try {
            var fileToDownload = fileStorageService.getDownloadFile(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(fileToDownload.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(Files.newInputStream(fileToDownload.toPath())));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}










//@RestController
//@RequestMapping("/file")
//public class FileManagerController {
//
//    @Autowired
//    private FileStorageService fileStorageService;  // Service for saving and retrieving files
//
//    private static final Logger log = Logger.getLogger(FileManagerController.class.getName());
//    private static final String FILE_UPLOAD_PATH = "/uploads";  // Base upload path
//
//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(
//        @RequestParam("file") MultipartFile file,
//        @RequestParam("usrId") String usrId,
//        @RequestParam("scrId") String scrId,
//        @RequestParam("fileDiv") String fileDiv,
//        @RequestParam(value = "extraPath", required = false) String extraPath) {
//
//        String filePath = createFilePath(fileDiv, extraPath);
//        String fileName = file.getOriginalFilename();
//        boolean fileCheck = checkFileExtension(file);
//
//        if (!fileCheck) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body("File Extension Error: Unsupported file type.");
//        }
//
//        try {
//            long fileSize = file.getSize();
//            fileStorageService.saveFile(file, filePath);  // Save file on disk
//
//
//            FileManagerInVo fileInfo = FileManagerInVo.builder()
//                .attach_file_id()
//                .attach_file_clcd()
//                .attach_file_expl()
//                .list()
//                .req_clcd()
//                .upd_yn()
//                .build();
//
//            saveFileData(fileInfo);  // Save metadata to database
//
//            return ResponseEntity.ok("File uploaded successfully");
//
//        } catch (Exception e) {
//            log.log(Level.SEVERE, "Error during file upload: " + fileName, e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("File upload failed due to server error.");
//        }
//    }
//
//    @GetMapping("/download")
//    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
//        try {
//            Path filePath = fileStorageService.getDownloadFile(fileName);  // Get file path
//
//            return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//                .contentLength(Files.size(filePath))
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(new InputStreamResource(Files.newInputStream(filePath)));
//        } catch (IOException e) {
//            log.log(Level.WARNING, "File not found: " + fileName, e);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(null);
//        }
//    }
//
//    // Utility Methods
//
//    private boolean checkFileExtension(MultipartFile file) {
//        String fileName = file.getOriginalFilename();
//        String fileExtension = fileName != null ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
//        return List.of("txt", "csv", "xlsx").contains(fileExtension.toLowerCase());  // Allowed types
//    }
//
//    private String createFilePath(String fileDiv, String extraPath) {
//        return FILE_UPLOAD_PATH + File.separator + fileDiv + (extraPath != null ? File.separator + extraPath : "");
//    }
//
//    private void saveFileData(FileManagerInVo fileInfo) {
//        // This method should interact with fileUplService to save metadata
//        // Placeholder to illustrate similar functionality as in the first code sample
//    }
//}
