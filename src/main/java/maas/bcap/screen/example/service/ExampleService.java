package maas.bcap.screen.example.service;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.module.az.az03.saz03v701u.SAZ03V701U;
import maas.bcap.module.az.az03.saz03v701u.SAZ03V701UInVo;
import maas.bcap.module.az.az03.saz03v701u.SAZ03V701UOutVo;
import maas.bcap.screen.example.dto.*;
import maas.bcap.module.ac.ac02.sac02f452r.SAC02F452R;
import maas.bcap.module.ac.ac02.sac02f452r.SAC02F452RInVo;
import maas.bcap.module.ac.ac02.sac02f452r.SAC02F452ROutVo;
import maas.bcap.module.ed.ed03.sed03f107r.SED03F107R;
import maas.bcap.module.ed.ed03.sed03f107r.SED03F107RInVo;
import maas.bcap.module.ed.ed03.sed03f107r.SED03F107ROutVo;
import mti.com.cipher.SHAEncryption;
import mti.com.system.SessionManager;
import mti.com.system.SessionVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
public class ExampleService {

    private static final Logger log = LogManager.getLogger(ExampleService.class);

    @Autowired
    private SED03F107R sed03f107r;

    @Autowired
    private SAC02F452R sac02f452r;

    @Autowired
    private SAZ03V701U saz03v701u;

    public LoginOutDto login(HttpServletRequest request, LoginInDto inDto, String screenId) throws Exception {

        /// Get Current Session
        SessionVo userVo = SessionManager.getUserData(request);

        if (userVo != null){
            log.info("LeRucco");
            log.info(userVo.toString());
        }

        String encryptedPassword = SHAEncryption.encrypt(inDto.getUser_id() + inDto.getPassword());

//        SAZ03V701UInVo saz03v701uInVo = SAZ03V701UInVo.builder()
//            .usr_id(inDto.getUser_id())
//            .usr_paswd(encryptedPassword)
//            .admin_yn("N")
//            .chnl_clcd("1")
//            .req_tp("I")
//            .build();
//        TelegramUserDataOutput<SAZ03V701UOutVo> saz03v701uResult = saz03v701u.call(request, saz03v701uInVo, screenId);
//        SAZ03V701UOutVo saz03v701uOutVo = saz03v701uResult.getOutput();

        String response = "00001070devaps01202410221334230014256400SAZ03V701U              MTI R                        devaps0120241022133423001425640020241022133423036   UNIT      192.168.1.3                     581CF8933F96            1787130271     020241022133423036   20241022133423725174  0  00        000       IAZAP0000                                                        EN                                                                                                                                             N00000425                     30Login success.                                                                                                                                                                                                                                                                                                                                                                                                  00D00000133                     1787130271     Yosua Sutandar                                    N1787130271                                 10Y@@";
        SAZ03V701UOutVo saz03v701uOutVo = SAZ03V701UOutVo.builder().build();
        TelegramUserDataOutput<SAZ03V701UOutVo> saz03v701uResult = InterfaceTelegramTest.response(response, saz03v701uOutVo);
        saz03v701uOutVo = saz03v701uResult.getOutput();
        log.info(saz03v701uOutVo.toString());

        request.getSession().invalidate();

        userVo = SessionVo.builder()
                .sUserId(inDto.getUser_id())
                .usrIno(saz03v701uOutVo.usr_ino)
                .sUserNm(saz03v701uOutVo.usr_nm)
                .usrCtgoCd(saz03v701uOutVo.usr_ctgo_cd)
                .adm_usr_yn(saz03v701uOutVo.adm_usr_yn)
                .build();

        SessionManager.setUserData(request, userVo);

        log.info("After Invalidate");
        log.info(userVo.toString());
        log.info(SessionManager.getUserData(request).toString());

        return LoginOutDto.builder()
                .usr_ctgo_cd(userVo.getUsrCtgoCd())
                .adm_usr_yn(userVo.getAdm_usr_yn())
                .build();
    }

    public void logout(HttpServletRequest request, String screenId) throws Exception {
        
        /// Get Current Session
        SessionVo userVo = SessionManager.getUserData(request);

        if (userVo == null) return;

        SAZ03V701UInVo saz03v701uInVo = SAZ03V701UInVo.builder()
                .usr_id(userVo.getSUserId())
                .admin_yn("N")
                .chnl_clcd("1") // 1:web 2:mobile
                .req_tp("O") // I:login O:logout
                .build();
                
        saz03v701u.call(request, saz03v701uInVo, screenId);
        
        SessionManager.destroyUserData(request);

        return;
    }

    public ExampleOutDto getListOfEDC(HttpServletRequest request, ExampleInDto inDto, String screenId) throws Exception {
        log.info(inDto.toString());

        /// SED03F107R
        SED03F107RInVo sed03F107RInVo = SED03F107RInVo.builder()
                .prd_tp_cd(inDto.getPrd_tp_cd())
                .sno(inDto.getSno())
                .srl_stat_cd(inDto.getSrl_stat_cd())
                .srl_st_cd(inDto.getSrl_st_cd())
                .prd_cd(inDto.getPrd_cd())
                .icc_id(inDto.getIcc_id())
                .build();
        TelegramUserDataOutput<SED03F107ROutVo> sed03f107rResult = sed03f107r.call(request, sed03F107RInVo, screenId);
        SED03F107ROutVo sed03F107ROutVo = sed03f107rResult.getOutput();

        /// SAC02F452R
        SAC02F452RInVo sac02F452RInVo = SAC02F452RInVo.builder()
                .page_no(inDto.getPage_no())
                .page_size(inDto.getPage_size())
                .mid(inDto.getMid())
                .auth_strt_date(inDto.getAuth_strt_date())
                .auth_end_date(inDto.getAuth_end_date())
                .build();
        TelegramUserDataOutput<SAC02F452ROutVo> sac02f452rResult = sac02f452r.call(request, sac02F452RInVo, screenId);
        SAC02F452ROutVo sac02F452ROutVo = sac02f452rResult.getOutput();

        List<ExampleOutSub1Dto> sub1Vos = new ArrayList<>();

        // Cara 1: Enhanced for loops
        // for (SAC02F452ROutSub1Vo le : sac02F452ROutVo.sub1Vos){
        // sub1Vos.add(
        // ED999OutSub1Vo.builder()
        // .auth_date(le.auth_date)
        // .pmt_date(le.pmt_date)
        // .card_no(le.card_no)
        // .auth_no(le.auth_no)
        // .sale_amt(le.sale_amt)
        // .pwcw_csh_amt(le.pwcw_csh_amt)
        // .dcctrans_yn(le.dcctrans_yn)
        // .build()
        // );
        // }

        // Cara 2: Stream loop
        if (sac02F452ROutVo.sub1Vos != null)
            sub1Vos = sac02F452ROutVo.sub1Vos.stream().map(le -> ExampleOutSub1Dto.builder()
                    .auth_date(le.auth_date)
                    .pmt_date(le.pmt_date)
                    .card_no(le.card_no)
                    .auth_no(le.auth_no)
                    .sale_amt(le.sale_amt)
                    .pwcw_csh_amt(le.pwcw_csh_amt)
                    .dcctrans_yn(le.dcctrans_yn)
                    .build()).toList();

        return ExampleOutDto.builder()
                .count(sed03F107ROutVo.count)
                .sno(sed03F107ROutVo.sno)
                .whous_cd(sed03F107ROutVo.whous_cd)
                .rack_no(sed03F107ROutVo.rack_no)
                .icc_id(sed03F107ROutVo.icc_id)
                .sim_no(sed03F107ROutVo.sim_no)
                .srl_stat_cd(sed03F107ROutVo.srl_stat_cd)
                .srl_st_cd(sed03F107ROutVo.srl_st_cd)
                .srl_loca_cd(sed03F107ROutVo.srl_loca_cd)
                .vend_no(sed03F107ROutVo.vend_no)
                .prd_cd(sed03F107ROutVo.prd_cd)
                .prd_nm(sed03F107ROutVo.prd_nm)

                .tot_cnt(sac02F452ROutVo.tot_cnt)
                .sub1Vos(sub1Vos)
                .build();
    }

    @Value("${aes.secret_key}")
    private String secretKey;

    public String decryptAES(String encryptedData, String iv) {
        try {
            // Decode Base64 for IV and encrypted data
            byte[] ivBytes = Base64.getDecoder().decode(iv);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

            // Setup secret key and IV
            SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            // Initialize cipher for decryption
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

            // Perform decryption
            byte[] original = cipher.doFinal(encryptedBytes);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "Decryption failed: " + e.getMessage();
        }
    }

    public String encryptAES(String plainText, String iv) {
        try {
            // Decode Base64 untuk IV
            byte[] ivBytes = Base64.getDecoder().decode(iv);

            // Setup secret key dan IV
            SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            // Inisialisasi cipher untuk enkripsi
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

            // Enkripsi data
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            // Encode hasil enkripsi ke Base64
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "Encryption failed: " + e.getMessage();
        }
    }

    public String generateRandomIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv);
    }
}
