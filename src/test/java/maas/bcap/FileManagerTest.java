package maas.bcap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import maas.bcap.dto.FileInSub1Vo;
import maas.bcap.dto.FileInVo;
import maas.bcap.dto.FileOutVo;
import maas.bcap.module.ac.ac02.sac02f452r.SAC02F452RInVo;
import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UInSub1Vo;
import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UInVo;
import maas.bcap.module.mc.mc04.smc04v041u.SMC04V041UInSub1Vo;
import maas.bcap.module.mc.mc04.smc04v041u.SMC04V041UInVo;
import mti.com.telegram.util.InterfaceTelegram;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

// .\mvnw -Dtest=FileManagerTest test
// .\mvnw -Dtest=FileManagerTest#uploadRegister test

@SpringBootTest
public class FileManagerTest {

    private static final Logger log = LogManager.getLogger(FileManagerTest.class);

    private TelegramUserDataInput buildTelegramUserDataInput() {
        TelegramUserDataInput userDataInput = new TelegramUserDataInput();
        userDataInput.setTx_code("SAZ06F010U");
        userDataInput.setClient_ip_no("0:0:0:0:0:0:0:1");
        // userDataInput.setClient_mac("");
        userDataInput.setScrn_id("FileUpload");
        userDataInput.setOp_id("1787130271");
        userDataInput.setSync_type("A");
        userDataInput.setRspn_svc_code("");
        userDataInput.setAsync_rspn_yn("0");
        userDataInput.setTtl_use_flag(0);
        userDataInput.setLang_type("EN");

        log.info(userDataInput);
        return userDataInput;
    }

    @Test
    @DisplayName("File Upload - Register")
    public void uploadRegister() throws Exception {

        // SAC02F452RInVo sac02f452rInVo = SAC02F452RInVo.builder()
        //         .page_no(1)
        //         .page_size(10)
        //         .mid("71000204442")
        //         .auth_strt_date("20240101")
        //         .auth_end_date("20240919")
        //         .build();
        // String request1 = InterfaceTelegramTest.request("SAC02F452R",
        //         sac02f452rInVo);
        // System.out.println(request1);

        // /////////////////////
        // SAC04V127UInSub1Vo inSub1Vo1 = SAC04V127UInSub1Vo.builder()
        //         .row_no(1)
        //         .pmt_date("20230331")
        //         .acq_mb_no("008")
        //         .mid("71000204442")
        //         .auth_batch_no("308800350789")
        //         .pmt_seq_no(1)
        //         .reg_date("20230331")
        //         .reg_seq_no(1)
        //         .build();

        // SAC04V127UInVo inVo2 = SAC04V127UInVo.builder()
        //         .sub1_vo(List.of(inSub1Vo1))
        //         .build();
        // String request2 = InterfaceTelegramTest.request("SAC04V127U", inVo2);
        // System.out.println(request2);

        // //////////////////////
        // SMC04V041UInSub1Vo smc04v041uInSub1Vo = SMC04V041UInSub1Vo.builder()
        //         .biz_clcd("U")
        //         .supics_trns_tp_seq_no("0074")
        //         .supics_trns_tp_cd("98")
        //         .appl_strt_date("20240905")
        //         .appl_end_date("29991231")
        //         .min_day_avg_sale_icr_rt(0)
        //         .build();

        // SMC04V041UInVo smc04v041uInVo = SMC04V041UInVo.builder()
        //         .sub1_vo(List.of(smc04v041uInSub1Vo))
        //         .build();

        // String request3 = InterfaceTelegramTest.request("SMC04V041U",
        //         smc04v041uInVo);
        // System.out.println(request3);

        ////////////////////
        TelegramUserDataInput userDataInput = buildTelegramUserDataInput();
        FileInSub1Vo inSub1Vo = FileInSub1Vo.builder()
                // .attach_file_id(null)
                // .attach_file_seq_no(null)
                // .attach_file_clcd(null)
                // .del_yn(null)
                .file_nm("v2.jpg")
                .upl_file_size(52277)
                // .upl_file_nm(null)
                .file_path("D:/bcap/div1")
                .pfr_rank(0)
                .inp_usr_id("1787130271")
                .inp_pgm_id("FileUpload")
                // .chng_usr_id(null)
                // .chng_pgm_id(null)
                .build();
        FileInVo inVo = FileInVo.builder()
                // .attach_file_id(null)
                .attach_file_clcd("div1")
                .attach_file_expl("desc1")
                .upd_yn("N")
                .req_clcd("U1")
                .sub1Vos(List.of(inSub1Vo))
                .build();

        String request9 = InterfaceTelegramTest.request("SAC02F452R", inVo);
        System.out.println(request9);

        log.info(inVo);

        FileOutVo outVo = FileOutVo.builder().build();

        TelegramUserDataOutput<FileOutVo> result = InterfaceTelegram.interfaceTuxedo(userDataInput, inVo, outVo);
        log.info(result);

        assertNull(null);
    }

}
