package maas.bcap.module.mc.mc04;


import maas.bcap.module.mc.mc04.smc04v041u.SMC04V041UInVo;
import maas.bcap.module.mc.mc04.smc04v041u.SMC04V041UInSub1Vo;
import maas.bcap.module.mc.mc04.smc04v041u.SMC04V041UOutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


public class SMC04V041UTest {

    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {

//        / SMC04V041U Multi
         SMC04V041UInSub1Vo smc04v041uInSub1Vo = SMC04V041UInSub1Vo.builder()
             .biz_clcd("U")
             .supics_trns_tp_seq_no("0074")
             .supics_trns_tp_cd("98")
             .appl_strt_date("20240905")
             .appl_end_date("29991231")
             .min_day_avg_sale_icr_rt(0)
             .build();

         SMC04V041UInVo smc04v041uInVo = SMC04V041UInVo.builder()
             .sub1_vo(List.of(smc04v041uInSub1Vo))
             .build();

        String request = InterfaceTelegramTest.request("SMC04V041U", smc04v041uInVo);

        Assertions.assertNotNull(request, "The request should not be null");
        Assertions.assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00001966devaps01202410161723440064257400SMC04V041U              MTI R                        devaps0120241016172344006425740020241016172344823   UNIT      172.16.20.34                    00090FAA0001WMC0402100  1997130388     020241016172344823   20241016172344917756  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00001029                     0000                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        0074@@";
        SMC04V041UOutVo outVo = SMC04V041UOutVo.builder().build();
        TelegramUserDataOutput<SMC04V041UOutVo> output = InterfaceTelegramTest.response(response, outVo);

        Assertions.assertNotNull(output, "The request should not be null");
        Assertions.assertInstanceOf(SMC04V041UOutVo.class, output.getOutput(), "Expected an instance of SMC04V042ROutVo");
    }

}
