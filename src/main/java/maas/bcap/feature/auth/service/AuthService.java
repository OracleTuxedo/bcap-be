package maas.bcap.feature.auth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.feature.auth.dto.LoginInDto;
import maas.bcap.module.az.az03.saz03v701u.SAZ03V701U;
import maas.bcap.module.az.az03.saz03v701u.SAZ03V701UInVo;
import maas.bcap.module.az.az03.saz03v701u.SAZ03V701UOutVo;
import mti.com.cipher.SHAEncryption;
import mti.com.system.SessionManager;
import mti.com.system.SessionVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;

@Service
public class AuthService {

    private static final Logger log = LogManager.getLogger(AuthService.class);

    @Autowired
    private SAZ03V701U saz03v701u;

    public void login(HttpServletRequest request, LoginInDto inDto, String screenId) throws Exception {

        /// Get Current Session Cookies HttpOnly
        SessionVo userSessionVo = SessionManager.getUserData(request);

        /// TODO Throw exceptionI
        if (userSessionVo != null) {
            log.info(userSessionVo.toString());
        }

        /// Call SAZ03V701U for notify DevonC about Login Activity
        // String encryptedPassword = SHAEncryption.encrypt(inDto.getUser_id() + inDto.getPassword());
        // SAZ03V701UInVo saz03v701uInVo = SAZ03V701UInVo.builder()
        //         .usr_id(inDto.getUser_id())
        //         .usr_paswd(encryptedPassword)
        //         .admin_yn("N")
        //         .chnl_clcd("1") // 1:web 2:mobile
        //         .req_tp("I") // I:login O:logout
        //         .build();
        // TelegramUserDataOutput<SAZ03V701UOutVo> saz03v701uResult = saz03v701u.call(request, saz03v701uInVo, screenId);
        // SAZ03V701UOutVo saz03v701uOutVo = saz03v701uResult.getOutput();

        /// Mock response from SAZ03V701U
        String response = "00001070devaps01202410221334230014256400SAZ03V701U              MTI R                        devaps0120241022133423001425640020241022133423036   UNIT      192.168.1.3                     581CF8933F96            1787130271     020241022133423036   20241022133423725174  0  00        000       IAZAP0000                                                        EN                                                                                                                                             N00000425                     30Login success.                                                                                                                                                                                                                                                                                                                                                                                                  00D00000133                     1787130271     Yosua Sutandar                                    N1787130271                                 10Y@@";
        SAZ03V701UOutVo saz03v701uOutVo = SAZ03V701UOutVo.builder().build();
        TelegramUserDataOutput<SAZ03V701UOutVo> saz03v701uResult = InterfaceTelegramTest.response(response, saz03v701uOutVo);
        saz03v701uOutVo = saz03v701uResult.getOutput();

        log.info(saz03v701uOutVo.toString());

        userSessionVo = SessionVo.builder()
                .sUserId(inDto.getUser_id())
                .usrIno(saz03v701uOutVo.usr_ino)
                .sUserNm(saz03v701uOutVo.usr_nm)
                .usrCtgoCd(saz03v701uOutVo.usr_ctgo_cd)
                .adm_usr_yn(saz03v701uOutVo.adm_usr_yn)
                .build();

        /// Destroy Current Session Cookies HttpOnly
        SessionManager.destroyUserData(request);

        /// Create Session Cookies HttpOnly to Client Browser
        SessionManager.setUserData(request, userSessionVo);

        log.info(SessionManager.getUserData(request).toString());

        return;
    }

    public void logout(HttpServletRequest request, String screenId) throws Exception {

        /// Get Current Session Cookies HttpOnly
        SessionVo userSessionVo = SessionManager.getUserData(request);

        /// TODO Throw exception
        if (userSessionVo == null) return;

        SAZ03V701UInVo saz03v701uInVo = SAZ03V701UInVo.builder()
                .usr_id(userSessionVo.getSUserId())
                .admin_yn("N")
                .chnl_clcd("1") // 1:web 2:mobile
                .req_tp("O") // I:login O:logout
                .build();

        saz03v701u.call(request, saz03v701uInVo, screenId);

        SessionManager.destroyUserData(request);

        return;
    }

}
