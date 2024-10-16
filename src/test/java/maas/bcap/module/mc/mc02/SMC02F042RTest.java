package maas.bcap.module.mc.mc02;

import maas.bcap.module.mc.mc02.smc02f042r.SMC02F042RInSub1Vo;
import maas.bcap.module.mc.mc02.smc02f042r.SMC02F042RInVo;
import maas.bcap.module.mc.mc02.smc02f042r.SMC02F042ROutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SMC02F042RTest {

    @Test
    @DisplayName("InVo to Request")
    public void request() throws Exception {
//00001150                                SMC02F042R              MTI S                                                                            UNIT      192.168.0.141                   0CDD2494CF5F                           020241016112941546                         00000      00000                                                                        EN                                                                                                                                              00000000                     11422                                                                                                 0000101422                                                                                                                                                                                                                                                            C                                                                                                                                                                                                                                                               00000000
//        List<SMC02F042RInSub1Vo> sub1Vo = List.of();

        SMC02F042RInVo inVo = SMC02F042RInVo.builder()
            .page_size(10)
            .inqr_clcd("1")
            .inqr_clcd_val("1422")
            .next_key("1422")
            .next_brnd_clcd("C")
//            .sub1_vo(sub1Vo)
            .build();
        String request = InterfaceTelegramTest.request("SMC02F042R", inVo);
        assertNotNull(request, "The request should not be null");
        assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00002772devaps01202410161129430024257400SMC02F042R              MTI R                        devaps0120241016112943002425740020241016112941546   UNIT      192.168.0.141                   0CDD2494CF5F                           020241016112941546   20241016112943291687  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000525                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             01The Transaction Successfully Ended.                                                                 D00001735                                                                                                                               10                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       3C1422test                                                                                                test                                                                                                                                                                                                         00000999999999999990000000000  000000000020210114YY M1422TESTING12314                                                                                        test                                                                                                                                                                                                        B00000999999999999990000000000  000000000029991231YY V1422Bengkeltest                                                                                         Bengkel                                                                                                                                                                                                      00000999999999999990000000000  000000000029991231YY @@";
        SMC02F042ROutVo outVo = SMC02F042ROutVo.builder().build();
        TelegramUserDataOutput<SMC02F042ROutVo> output = InterfaceTelegramTest.response(response, outVo);

        assertNotNull(output, "The request shoul not be null");
        assertInstanceOf(SMC02F042ROutVo.class, output.getOutput(), "Excepted an instance of SMC02F042ROutVo");

    }

    @Test
    @DisplayName("Response throw Exception")
    public void responseException() {
        String response = "abc";
        SMC02F042ROutVo outVo = SMC02F042ROutVo.builder().build();

        assertThatThrownBy(() -> InterfaceTelegramTest.response(response, outVo))
            .isInstanceOf(java.lang.ArrayIndexOutOfBoundsException.class)
            .hasMessageContaining("out of bounds");
    }

}