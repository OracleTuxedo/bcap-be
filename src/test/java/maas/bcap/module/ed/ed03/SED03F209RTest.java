package maas.bcap.module.ed.ed03;


import maas.bcap.module.ed.ed03.sed03f209r.SED03F209RInVo;
import maas.bcap.module.ed.ed03.sed03f209r.SED03F209ROutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SED03F209RTest {

    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {
        SED03F209RInVo sed03f209rInVo = SED03F209RInVo.builder()
            .whous_cd("Z900")
            .build();
        String request = InterfaceTelegramTest.request("SED03F209R", sed03f209rInVo);
        Assertions.assertNotNull(request, "The request should not be null");
        Assertions.assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00002276devaps01202409261659400015467300SED03F209R              MTI R                        devaps0120240926165940001546730020240926165937773   UNIT      172.16.20.6                     00090FAA0001                           020240926165937773   20240926165940042641  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000525                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                              1The Transaction Successfully Ended.                                                                 D00001239                           1110001     Building A, 2nd Floor, 201                                                                          10002     Building A, 2nd Floor, 201                                                                          10003     Building A, 2nd Floor, 201                                                                          10005     Building A, 2nd Floor, 201                                                                          10082     Dev by Sung 1                                                                                       10086     Dev by Sung 5                                                                                       10087     Dev by Sung 6                                                                                       10088     Test by Sung 6                                                                                      10089     Sung by Dev 6                                                                                       10090     RACK NO 5                                                                                           10091     SUNG TEST 10                                                                                        @@";
        SED03F209ROutVo outVo = SED03F209ROutVo.builder().build();
        TelegramUserDataOutput<SED03F209ROutVo> output = InterfaceTelegramTest.response(response, outVo);
        Assertions.assertNotNull(output, "The request should not be null");
        Assertions.assertInstanceOf(SED03F209ROutVo.class, output.getOutput(), "Expected an instance of SED03F209ROutVo");
    }
}
