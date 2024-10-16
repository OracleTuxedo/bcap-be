package maas.bcap.module.az.az05;

import maas.bcap.module.az.az05.saz05f030r.SAZ05F030RInVo;
import maas.bcap.module.az.az05.saz05f030r.SAZ05F030ROutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SAZ05F030RTest {
    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {
        SAZ05F030RInVo saz05F030RInVo = SAZ05F030RInVo.builder()
            .page_no(1)
            .page_size(1)
            .usr_id("1684130034")
            .apvl_req_clcd("U")
            .emp_nm("Jaka Sembung")
            .build();
        String request = InterfaceTelegramTest.request("SAZ05F030R", saz05F030RInVo);
        // 00000610                                SAZ05F030R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241016125737648                         00000      00000                                                                        EN                                                                                                                                              00000000                     0000010000011684130034     UJaka Sembung
        // 00000612Falah   202410161333590020000000SAZ05F030R              MTI S                        Falah   20241016133359002000000020241016133359000048WEB       172.16.20.11                                WED030120H N1787130271     020241016133359000055                    A000000      00010                                                                        EN                                                                                                                                             D00000109                     0000010000011684130034     UJaka Sembung                                                @@

        Assertions.assertNotNull(request, "The request should not be null");
        Assertions.assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00001514devaps01202410161257370034257500SAZ05F030R              MTI R                        devaps0120241016125737003425750020241016125737648   UNIT      192.168.1.16                    088FC37E596F                           020241016125737648   20241016125737089670  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000577                     N     3         3       31684130034     1684130034   Jaka Sembung                                                1675110011     1675110011   Rony Andry Anthony Sihotang                                 A1684130034     1684130034   Jaka Sembung                                                1684130034     1684130034   Jaka Sembung                                                A1684130034     1684130034   Jaka Sembung                                                1691130063     1691130063   Andrew Tasidjawa                                            A@@";
        SAZ05F030ROutVo outVo = SAZ05F030ROutVo.builder().build();
        TelegramUserDataOutput<SAZ05F030ROutVo> output = InterfaceTelegramTest.response(response, outVo);

        Assertions.assertNotNull(output, "The request should not be null");
        Assertions.assertInstanceOf(SAZ05F030ROutVo.class, output.getOutput(), "Expected an instance of SAZ05F030ROutVo");
    }
}
