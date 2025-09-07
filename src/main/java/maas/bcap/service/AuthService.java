package maas.bcap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.LogOutDto;
import maas.bcap.dto.LoginInDto;
import maas.bcap.module.az.az03.saz03f000u.SAZ03F000U;
import maas.bcap.module.az.az03.saz03f000u.SAZ03F000UInVo;
import maas.bcap.module.az.az03.saz03f000u.SAZ03F000UOutVo;
import maas.bcap.security.JwtUtil;
import mti.com.cipher.SHAEncryption;
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

    public String login(HttpServletRequest request, LoginInDto inDto) throws NoSuchAlgorithmException {
        /// Password encryption is FE Responsbility
        // final String encryptedPassword = SHAEncryption.encrypt(inDto.getUserId() + inDto.getPassword());

        /// TODO Only Development Local
        /// Call SAZ03F000U for notify DevonC about Login Activity
        // final SAZ03F000UInVo saz03f000uInVo = SAZ03F000UInVo.builder()
        //         .usr_conn_clcd("I")
        //         .usr_id(inDto.getUserId())
        //         .usr_paswd(inDto.getEncryptionPassword())
        //         .build();
        // log.info("SAZ03F000UInVo [{}]", saz03f000uInVo.toString());
        // final TelegramUserDataOutput<SAZ03F000UOutVo> saz03v701uResult = saz03f000u.call(
        //         request,
        //         saz03f000uInVo,
        //         inDto.getScreenId());
        // final SAZ03F000UOutVo saz03f000uOutVo = saz03v701uResult.getOutput();
        // log.info("SAZ03F000UOutVo [{}]", saz03f000uOutVo.toString());

        /// Generate JWT Token with AuthInfoDto as Payload
        final AuthInfoDto authInfoDto = AuthInfoDto.builder()
                .userId(inDto.getUserId())
                .encryptionPassword(inDto.getEncryptionPassword())
                .build();
        log.info("AuthInfoDto [{}]", authInfoDto.toString());

        final String token = jwtUtil.generateToken(authInfoDto);
        log.info("token [{}]", token);

        return token;
    }

    public void logout(HttpServletRequest request, LogOutDto outDto) {
        /// TODO Only Development Local
        /// Call SAZ03F000U for notify DevonC about Logout Activity
        // final SAZ03F000UInVo saz03f000uInVo = SAZ03F000UInVo.builder()
        //         .usr_conn_clcd("O")
        //         .usr_id(outDto.getUserId())
        //         .build();
        // log.info("SAZ03F000UInVo [{}]", saz03f000uInVo.toString());
        // final TelegramUserDataOutput<SAZ03F000UOutVo> saz03v701uResult = saz03f000u.call(
        //         request,
        //         saz03f000uInVo,
        //         outDto.getScreenId());
        // final Optional<SAZ03F000UOutVo> saz03f000uOutVoOptional = Optional.of(saz03v701uResult.getOutput());
        // if (saz03f000uOutVoOptional.isPresent())
        //     log.info("SAZ03F000UOutVo [{}]", saz03f000uOutVoOptional.get().toString());
        return;
    }
}
