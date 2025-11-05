package maas.bcap.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.LogOutDto;
import maas.bcap.dto.LoginInDto;
import maas.bcap.dto.LoginOutDto;
import maas.bcap.security.CurrentAuthInfoDto;
import maas.bcap.security.JwtUtil;
import maas.bcap.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LogManager.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginOutDto> login(
            javax.servlet.http.HttpServletRequest request,
            @RequestBody LoginInDto inDto) throws Exception {
        log.info("LoginInDto [{}]", inDto.toString());

        return authService.login(request, inDto);
    }

    @GetMapping("/me1")
    public AuthInfoDto me1(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        log.info("Token [{}]", token);
        final AuthInfoDto authInfoDto = jwtUtil.extractAuthInfo(token);
        log.info("AuthInfoDto [{}]", authInfoDto.toString());
        return authInfoDto;
    }

    @GetMapping("/me2")
    public AuthInfoDto me2() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final AuthInfoDto authInfoDto = (AuthInfoDto) auth.getPrincipal();
        return authInfoDto;
    }

    @GetMapping("/me3")
    public AuthInfoDto me3(Authentication auth) {
        final AuthInfoDto authInfoDto = (AuthInfoDto) auth.getPrincipal();
        return authInfoDto;
    }

    @GetMapping("/me4")
    public AuthInfoDto me3(@CurrentAuthInfoDto AuthInfoDto authInfoDto) {
        return authInfoDto;
    }

    @PostMapping("/logout")
    public void logout(
            HttpServletRequest request,
            @RequestBody LogOutDto outDto) throws Exception {
        authService.logout(request, outDto);
    }
}
