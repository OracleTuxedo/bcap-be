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

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
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
    public MessageTransferOutDto messageTransfer(HttpServletRequest request, @RequestBody MessageTransferInDto inDto)
            throws Exception {
        log.info(inDto.toString());
        return messageService.messageTransfer(request, inDto);
    }

    @PostMapping(value = "/forward/weblogic", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> forwardWeblogic(HttpServletRequest request, @RequestBody byte[] requestToTuxedo) throws ServletException, IOException, Exception {
        byte[] responstFromTuxedo = messageService.forwardWeblogic(request, requestToTuxedo);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(responstFromTuxedo);
    }

}
