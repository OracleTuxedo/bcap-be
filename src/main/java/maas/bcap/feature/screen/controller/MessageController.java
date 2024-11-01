package maas.bcap.feature.screen.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.feature.screen.dto.MessageTransferInDto;
import maas.bcap.feature.screen.dto.MessageTransferOutDto;
import maas.bcap.feature.screen.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
    private static final Logger log = LogManager.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    public MessageTransferOutDto messageTransfer(HttpServletRequest request, @RequestBody MessageTransferInDto inDto) throws Exception {
        return messageService.messageTransfer(request, inDto);
    }
}
