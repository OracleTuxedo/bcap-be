package maas.bcap.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.LoginInDto;
import maas.bcap.security.JwtUtil;
import maas.bcap.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LogManager.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public void login(
            jakarta.servlet.http.HttpServletRequest request,
            @RequestBody LoginInDto inDto) throws Exception {
        log.info("LoginInDto [{}]", inDto.toString());
        final AuthInfoDto authInfoDto = AuthInfoDto.builder()
                .id(1L)
                .userId(inDto.getUser_id())
                .password(inDto.getPassword())
                .name("Le Rucco")
                .build();

        String token = jwtUtil.generateToken(authInfoDto);
        log.info("Token [{}]", token);
        // authService.login(request, inDto, "TODO");
        return;
    }

    @GetMapping("/me")
    public AuthInfoDto me(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        log.info("Token [{}]", token);
        final AuthInfoDto authInfoDto = jwtUtil.extractAuthInfo(token);
        log.info("AuthInfoDto [{}]", authInfoDto.toString());
        return authInfoDto;
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) throws Exception {
        authService.logout(request, "TODO");
        return;
    }
}
