package maas.bcap.module.az.az03;

import maas.bcap.module.az.az03.saz03f000u.SAZ03F000UInVo;
import maas.bcap.module.az.az03.saz03f000u.SAZ03F000UOutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SAZ03F000UTest {

    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {
        SAZ03F000UInVo saz03F000UInVo = SAZ03F000UInVo.builder()
            .usr_conn_clcd("I")
            .usr_id("0000000001")
            .usr_paswd("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            .build();

        String request = InterfaceTelegramTest.request("SAZ03F000U", saz03F000UInVo);

        Assertions.assertNotNull(request, "The request should not be null");
        Assertions.assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00000968devaps01202410221006010014256100SAZ03F000U              MTI R                        devaps0120241022100601001425610020241022100600732   UNIT      192.168.1.3                     581CF8933F96                           020241022100600732   20241022100601445163  0  00        000       IAZAP0000                                                        EN                                                                                                                                             N00000425                     30Login success.                                                                                                                                                                                                                                                                                                                                                                                                  00D00000031                              0@@";
        SAZ03F000UOutVo outVo = SAZ03F000UOutVo.builder().build();
        TelegramUserDataOutput<SAZ03F000UOutVo> output = InterfaceTelegramTest.response(response, outVo);

        Assertions.assertNotNull(output, "The request should not be null");
        Assertions.assertInstanceOf(SAZ03F000UOutVo.class, output.getOutput(), "Expected an instance of SED03F209ROutVo");
    }

}
