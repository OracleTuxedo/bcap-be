package le.bcap.ac.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import le.bcap.ac.dto.SAC02F452RInDto;
import le.bcap.ac.dto.SAC02F452ROutDto;
import le.bcap.ac.service.SAC02F452RService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/SAC02F452R")
public class SAC02F452RController {

    private final SAC02F452RService sac02f452rService;

    public SAC02F452RController(SAC02F452RService sac02f452rService){
        this.sac02f452rService = sac02f452rService;
    }

    @PostMapping("/")
    public SAC02F452ROutDto salesTransactionListAjax(HttpServletRequest request, @RequestBody SAC02F452RInDto dto) throws Exception {
//        return dto;
        return sac02f452rService.getListTransaction(request, dto);
    }

}
