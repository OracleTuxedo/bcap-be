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
import maas.bcap.security.JwtUtil;
import mti.com.cipher.SHAEncryption;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramHeader;
import mti.com.telegram.vo.TelegramUserDataOutput;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AuthService {
    private static final Logger log = LogManager.getLogger(AuthService.class);

    @Autowired
    private SAZ03F000U saz03f000u;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseEntity<LoginOutDto> login(HttpServletRequest request, LoginInDto inDto)
            throws NoSuchAlgorithmException, TelegramNestedRuntimeException, Exception {
        try {
            /// Password encryption is FE Responsbility
            // final String encryptedPassword = SHAEncryption.encrypt(inDto.getUserId() +
            /// inDto.getPassword());

            /// TODO Only Development Local
            /// Call SAZ03F000U for notify DevonC about Login Activity
            final SAZ03F000UInVo saz03f000uInVo = SAZ03F000UInVo.builder()
                    .usr_conn_clcd(inDto.getAppType())
                    .usr_id(inDto.getUserId())
                    .usr_paswd(inDto.getEncryptionPassword())
                    .build();
            log.info("SAZ03F000UInVo [{}]", saz03f000uInVo.toString());
            final TelegramUserDataOutput<SAZ03F000UOutVo> saz03f000uResult = saz03f000u.call(
                    request,
                    saz03f000uInVo,
                    inDto.getScreenId());
            log.info("saz03f000uResult [{}]", saz03f000uResult);
            final SAZ03F000UOutVo saz03f000uOutVo = saz03f000uResult.getOutput();
            log.info("SAZ03F000UOutVo [{}]", saz03f000uOutVo.toString());

            final TelegramHeader saz03f000uHeader = saz03f000uResult.getHeader();
            final String errCode = saz03f000uHeader.getErr_code();
            log.info("saz03f000uHeader [{}]", saz03f000uHeader);

            if (errCode.equals("WAZAP0060") || errCode.equals("WAZAP0058")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginOutDto.builder()
                        .status("Unauthorized")
                        .message("Change Password Needed")
                        .build());
            }

            /// Generate JWT Token with AuthInfoDto as Payload
            final AuthInfoDto authInfoDto = AuthInfoDto.builder()
                    .userId(inDto.getUserId())
                    .encryptionPassword(inDto.getEncryptionPassword())
                    .build();
            log.info("AuthInfoDto [{}]", authInfoDto.toString());

            final String token = jwtUtil.generateToken(authInfoDto);
            log.info("token [{}]", token);

            return ResponseEntity.ok().body(LoginOutDto.builder()
                    .status("OK")
                    .message("OK")
                    .token(token)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginOutDto.builder()
                    .status("Unauthorized")
                    .message(e.getMessage())
                    .build());
        }

    }

    public void logout(HttpServletRequest request, LogOutDto outDto) {
        /// TODO Only Development Local
        /// Call SAZ03F000U for notify DevonC about Logout Activity
        // final SAZ03F000UInVo saz03f000uInVo = SAZ03F000UInVo.builder()
        // .usr_conn_clcd("O")
        // .usr_id(outDto.getUserId())
        // .build();
        // log.info("SAZ03F000UInVo [{}]", saz03f000uInVo.toString());
        // final TelegramUserDataOutput<SAZ03F000UOutVo> saz03v701uResult =
        /// saz03f000u.call(
        // request,
        // saz03f000uInVo,
        // outDto.getScreenId());
        // final Optional<SAZ03F000UOutVo> saz03f000uOutVoOptional =
        /// Optional.of(saz03v701uResult.getOutput());
        // if (saz03f000uOutVoOptional.isPresent())
        // log.info("SAZ03F000UOutVo [{}]", saz03f000uOutVoOptional.get().toString());
        return;
    }
}
