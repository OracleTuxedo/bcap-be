package maas.bcap.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import maas.bcap.dto.MessageTransferInDto;
import maas.bcap.dto.MessageTransferOutDto;
import maas.bcap.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
    private static final Logger log = LogManager.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @PostMapping("")
    public MessageTransferOutDto messageTransfer(HttpServletRequest request, @Valid @RequestBody MessageTransferInDto inDto)
            throws Exception {
        log.info("messageTransfer request received");
        return messageService.messageTransfer(request, inDto);
    }

    private static final int MAX_FORWARD_PAYLOAD_SIZE = 1024 * 1024; // 1 MB

    @PostMapping(value = "/forward/weblogic", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> forwardWeblogic(HttpServletRequest request, @RequestBody byte[] requestToTuxedo) throws ServletException, IOException, Exception {
        if (requestToTuxedo == null || requestToTuxedo.length == 0) {
            return ResponseEntity.badRequest().build();
        }
        if (requestToTuxedo.length > MAX_FORWARD_PAYLOAD_SIZE) {
            log.warn("forwardWeblogic payload too large: {} bytes", requestToTuxedo.length);
            return ResponseEntity.status(413).build();
        }
        byte[] responstFromTuxedo = messageService.forwardWeblogic(request, requestToTuxedo);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(responstFromTuxedo);
    }

}
