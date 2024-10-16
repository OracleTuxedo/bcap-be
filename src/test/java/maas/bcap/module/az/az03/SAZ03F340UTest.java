package maas.bcap.module.az.az03;

import maas.bcap.module.az.az03.saz03f340u.SAZ03F340UInVo;
import maas.bcap.module.az.az03.saz03f340u.SAZ03F340UOutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SAZ03F340UTest {

    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {
        SAZ03F340UInVo sed03f209rInVo = SAZ03F340UInVo.builder()
            .usr_id("M000000013")
            .menu_id("MC0510100")
            .data_stat_cd("U")
            .build();

        String request = InterfaceTelegramTest.request("SED03F209R", sed03f209rInVo);

        Assertions.assertNotNull(request, "The request should not be null");
        Assertions.assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00000966devaps01202410161126550014257700SAZ03F340U              MTI R                        devaps0120241016112655001425770020241016112655216   UNIT      172.16.20.34                    00090FAA0001                           020241016112655216   20241016112655282058  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000029                            0@@";
        SAZ03F340UOutVo outVo = SAZ03F340UOutVo.builder().build();
        TelegramUserDataOutput<SAZ03F340UOutVo> output = InterfaceTelegramTest.response(response, outVo);

        Assertions.assertNotNull(output, "The request should not be null");
        Assertions.assertInstanceOf(SAZ03F340UOutVo.class, output.getOutput(), "Expected an instance of SED03F209ROutVo");
    }

}
