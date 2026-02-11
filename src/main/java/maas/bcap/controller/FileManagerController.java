package maas.bcap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.FileDownloadInDto;
import maas.bcap.dto.FileUploadInDto;
import maas.bcap.security.CurrentAuthInfoDto;
import maas.bcap.service.FileManagerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequestMapping("file-manager")
public class FileManagerController {

    private static final Logger log = LogManager.getLogger(FileManagerController.class);

    @Autowired
    private FileManagerService fileManagerService;

    @PostMapping("/upload")
    public void upload(
            HttpServletRequest request,
            HttpServletResponse response,
            @CurrentAuthInfoDto AuthInfoDto authInfoDto,
            @Valid @ModelAttribute FileUploadInDto inDto,
            @RequestPart("files") List<MultipartFile> files) throws Exception {

        log.info("Upload request from userId [{}], fileCount [{}]", authInfoDto.getUserId(), files != null ? files.size() : 0);
        log.debug("inDto : {}", inDto);
        log.debug("Files : {}", files);
        fileManagerService.upload(request, authInfoDto, inDto, files);
    }

    @GetMapping("/download")
    public void download(
            HttpServletRequest request,
            HttpServletResponse response,
            @CurrentAuthInfoDto AuthInfoDto authInfoDto,
            @Valid @ModelAttribute FileDownloadInDto inDto) throws Exception {
        fileManagerService.download(request, response, authInfoDto, inDto);
        return;
    }

}
