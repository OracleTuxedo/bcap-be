package maas.bcap.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.LogOutDto;
import maas.bcap.dto.LoginInDto;
import maas.bcap.dto.LoginOutDto;
import maas.bcap.dto.RefreshTokenInDto;
import maas.bcap.dto.RefreshTokenOutDto;
import maas.bcap.security.CurrentAuthInfoDto;
import maas.bcap.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LogManager.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginOutDto> login(
            javax.servlet.http.HttpServletRequest request,
            @Valid @RequestBody LoginInDto inDto) throws Exception {
        log.info("Login attempt for userId [{}]", inDto.getUserId());

        return authService.login(request, inDto);
    }

    @GetMapping("/me")
    public AuthInfoDto me(@CurrentAuthInfoDto AuthInfoDto authInfoDto) {
        return authInfoDto;
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenOutDto> refresh(@Valid @RequestBody RefreshTokenInDto inDto) {
        return authService.refresh(inDto);
    }

    @PostMapping("/logout")
    public void logout(
            HttpServletRequest request,
            @Valid @RequestBody LogOutDto outDto) throws Exception {
        authService.logout(request, outDto);
    }
}
