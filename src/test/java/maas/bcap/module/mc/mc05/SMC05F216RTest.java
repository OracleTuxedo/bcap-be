package maas.bcap.module.mc.mc05;

import maas.bcap.module.mc.mc05.smc05f216r.SMC05F216RInVo;
import maas.bcap.module.mc.mc05.smc05f216r.SMC05F216ROutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SMC05F216RTest {
    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {
        SMC05F216RInVo smc05F216RInVo = SMC05F216RInVo.builder()
            .biz_clcd("R")
            .page_no(1)
            .page_size(1)
            .usr_id("1680110013")
            .usr_nm("Bayu Sulistyo")
            .build();
        String request = InterfaceTelegramTest.request("SMC05F216R", smc05F216RInVo);
        // 00000677                                SMC05F216R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241016160009897                         00000      00000                                                                        EN                                                                                                                                              00000000                     R000001000001                           1680110013     Bayu Sulistyo
        // 00000679Falah   202410161605110020000000SMC05F216R              MTI S                        Falah   20241016160511002000000020241016160511000403WEB       172.16.20.11                                WED030120H N1787130271     020241016160511000415                    A000000      00010                                                                        EN                                                                                                                                             D00000176                     R000001000001                           1680110013     Bayu Sulistyo                                                                                       @@

        Assertions.assertNotNull(request, "The request should not be null");
        Assertions.assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00002734devaps01202410161600090044257500SMC05F216R              MTI R                        devaps0120241016160009004425750020241016160009897   UNIT      192.168.1.16                    088FC37E596F                           020241016160009897   20241016160009072517  0  00        000       NAZAP0001                                                        EN                                                                                                                                             N00000525                     30inquiry process success.                                                                                                                                                                                                                                                                                                                                                                                        01inquiry process success.                                                                            D00001697                     Y     2         1       11680110013     Bayu Sulistyo                                                                                       00001                                                                                                                                                                                                                                                                                                                                                           082122793605        bayus@gmail.com                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           C2016060129991231EN   1688130057     WMC0570300 20170124162000139   1688130057     WMC0570300 20170124162000139   @@";
        SMC05F216ROutVo outVo = SMC05F216ROutVo.builder().build();
        TelegramUserDataOutput<SMC05F216ROutVo> output = InterfaceTelegramTest.response(response, outVo);

        Assertions.assertNotNull(output, "The request should not be null");
        Assertions.assertInstanceOf(SMC05F216ROutVo.class, output.getOutput(), "Expected an instance of SMC05F216ROutVo");
    }
}
