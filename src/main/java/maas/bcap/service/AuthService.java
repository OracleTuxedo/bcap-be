package maas.bcap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.LogOutDto;
import maas.bcap.dto.LoginInDto;
import maas.bcap.dto.LoginOutDto;
import maas.bcap.module.az.az03.saz03f000u.SAZ03F000U;
import maas.bcap.module.az.az03.saz03f000u.SAZ03F000UInVo;
import maas.bcap.module.az.az03.saz03f000u.SAZ03F000UOutVo;
import maas.bcap.security.JwtBlacklist;
import maas.bcap.security.JwtUtil;
import maas.bcap.security.LoginRateLimiter;
import maas.bcap.security.RefreshTokenStore;
import maas.bcap.dto.RefreshTokenInDto;
import maas.bcap.dto.RefreshTokenOutDto;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramHeader;
import mti.com.telegram.vo.TelegramUserDataOutput;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AuthService {
    private static final Logger log = LogManager.getLogger(AuthService.class);

    @Autowired
    private SAZ03F000U saz03f000u;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    LoginRateLimiter rateLimiter;

    @Autowired
    RefreshTokenStore refreshTokenStore;

    @Autowired
    JwtBlacklist jwtBlacklist;

    public ResponseEntity<LoginOutDto> login(HttpServletRequest request, LoginInDto inDto)
            throws NoSuchAlgorithmException, TelegramNestedRuntimeException, Exception {
        try {
            /// Rate limit check
            if (rateLimiter.isBlocked(inDto.getUserId())) {
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(LoginOutDto.builder()
                        .message("Too many login attempts. Please try again later.")
                        .build());
            }

            /// Call SAZ03F000U for notify DevonC about Login Activity
            final SAZ03F000UInVo saz03f000uInVo = SAZ03F000UInVo.builder()
                    .usr_conn_clcd(inDto.getAppType())
                    .usr_id(inDto.getUserId())
                    .usr_paswd(inDto.getEncryptionPassword())
                    .build();
            log.info("SAZ03F000UInVo [userId={}]", inDto.getUserId());
            final TelegramUserDataOutput<SAZ03F000UOutVo> saz03f000uResult = saz03f000u.call(
                    request,
                    saz03f000uInVo,
                    inDto.getScreenId());
            log.info("saz03f000uResult received for userId [{}]", inDto.getUserId());
            final SAZ03F000UOutVo saz03f000uOutVo = saz03f000uResult.getOutput();
            if (saz03f000uOutVo == null) {
                rateLimiter.recordFailedAttempt(inDto.getUserId());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginOutDto.builder()
                        .message("Wrong Credentials")
                        .build());
            }
            log.info("SAZ03F000UOutVo received for userId [{}]", inDto.getUserId());

            final TelegramHeader saz03f000uHeader = saz03f000uResult.getHeader();
            final String errCode = saz03f000uHeader.getErr_code();
            log.info("saz03f000uHeader errCode [{}]", errCode);

            if (errCode.equals("WAZAP0060") || errCode.equals("WAZAP0058")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginOutDto.builder()
                        .message("Change Password Needed")
                        .build());
            }

            /// Generate JWT Token with AuthInfoDto as Payload
            final AuthInfoDto authInfoDto = AuthInfoDto.builder()
                    .userId(inDto.getUserId())
                    .build();
            log.info("Generating token for userId [{}]", inDto.getUserId());

            final String token = jwtUtil.generateToken(authInfoDto);
            log.info("Token generated for userId [{}]", inDto.getUserId());

            rateLimiter.reset(inDto.getUserId());

            final String refreshToken = refreshTokenStore.generateRefreshToken(inDto.getUserId());

            return ResponseEntity.ok().body(LoginOutDto.builder()
                    .message("OK")
                    .token(token)
                    .refreshToken(refreshToken)
                    .build());
        } catch (Exception e) {
            log.error("Login failed for userId [{}]: {}", inDto.getUserId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginOutDto.builder()
                    .message("Authentication failed")
                    .build());
        }

    }

    public ResponseEntity<RefreshTokenOutDto> refresh(RefreshTokenInDto inDto) {
        String userId = refreshTokenStore.validateAndGetUserId(inDto.getRefreshToken());
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(RefreshTokenOutDto.builder()
                    .message("Invalid or expired refresh token")
                    .build());
        }

        /// Invalidate old refresh token (rotation)
        refreshTokenStore.invalidate(inDto.getRefreshToken());

        /// Generate new tokens
        final AuthInfoDto authInfoDto = AuthInfoDto.builder()
                .userId(userId)
                .build();

        try {
            final String newAccessToken = jwtUtil.generateToken(authInfoDto);
            final String newRefreshToken = refreshTokenStore.generateRefreshToken(userId);

            return ResponseEntity.ok().body(RefreshTokenOutDto.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .message("OK")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RefreshTokenOutDto.builder()
                    .message("Token generation failed")
                    .build());
        }
    }

    public void logout(HttpServletRequest request, LogOutDto outDto) {
        /// Blacklist current access token
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtBlacklist.blacklist(authHeader.substring(7));
        }

        /// Invalidate refresh token if provided
        if (outDto.getRefreshToken() != null) {
            refreshTokenStore.invalidate(outDto.getRefreshToken());
        }
    }
}
