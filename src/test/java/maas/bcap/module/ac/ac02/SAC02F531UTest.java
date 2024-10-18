package maas.bcap.module.ac.ac02;

import maas.bcap.module.ac.ac02.sac02f531u.SAC02F531UInVo;
import maas.bcap.module.ac.ac02.sac02f531u.SAC02F531UOutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SAC02F531UTest {

    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {
        SAC02F531UInVo sac02f531uInVo = SAC02F531UInVo.builder()
            .bin_no("230650068")
            .mcc_cd("3018")
            .prod_cd("MDP")
            .ird("PS")
            .seq_no(1)
            .chng_ird("YH")
            .card_no("75123463027008772761025")
            .stat_cd("N")
            .data_stat_cd("I")
            .build();
        String request = InterfaceTelegramTest.request("SAC02F531U", sac02f531uInVo);

        assertNotNull(request, "The request should not be null");
        assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00000959devaps01202410181645490014242700SAC02F531U              MTI R                        devaps0120241018164549001424270020241018164547187   UNIT      192.168.137.1                   8E96E6A577A5                           020241018164547187   20241018164549163762  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000022                      @@";
        SAC02F531UOutVo outVo = SAC02F531UOutVo.builder().build();
        TelegramUserDataOutput<SAC02F531UOutVo> output = InterfaceTelegramTest.response(response, outVo);

        assertNotNull(output, "The request should not be null");
        assertInstanceOf(SAC02F531UOutVo.class, output.getOutput(), "Expected an instance of SAC02F531UOutVo");
    }

    @Test
    @DisplayName("Response Throw Exception")
    public void responseException() {
        String response = "aaa";
        SAC02F531UOutVo outVo = SAC02F531UOutVo.builder().build();

        assertThatThrownBy(() -> InterfaceTelegramTest.response(response, outVo))
            .isInstanceOf(java.lang.ArrayIndexOutOfBoundsException.class)
            .hasMessageContaining("out of bounds");

    }
}
