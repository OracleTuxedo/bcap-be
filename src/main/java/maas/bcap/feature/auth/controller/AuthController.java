package maas.bcap.feature.auth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.feature.auth.dto.LoginInDto;
import maas.bcap.feature.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LogManager.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public void login(HttpServletRequest request, LoginInDto inDto) throws Exception {
        log.debug("LoginInDto [" + inDto.toString() + "]");
        authService.login(request, inDto, "TODO");
        return;
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) throws Exception {
        authService.logout(request, "TODO");
        return;
    }
}
