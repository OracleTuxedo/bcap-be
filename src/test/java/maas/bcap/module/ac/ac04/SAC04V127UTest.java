package maas.bcap.module.ac.ac04;

import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UInSub1Vo;
import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UInVo;
import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UOutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SAC04V127UTest {

    @Test
    @DisplayName("InVo to Request")
    public void request() throws Exception {

        SAC04V127UInSub1Vo inSub1Vo1 = SAC04V127UInSub1Vo.builder()
            .row_no(1)
            .pmt_date("20230331")
            .acq_mb_no("008")
            .mid("71000204442")
            .auth_batch_no("308800350789")
            .pmt_seq_no(1)
            .reg_date("20230331")
            .reg_seq_no(1)
            .build();

        SAC04V127UInVo inVo = SAC04V127UInVo.builder()
            .sub1_vo(List.of(inSub1Vo1))
            .build();
        String request = InterfaceTelegramTest.request("SAC04V127U", inVo);
        assertNotNull(request, "The request should not be null");
        assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00000987devaps01202410091731150024239400SAC04V127U              MTI R                        devaps0120241009173115002423940020241009173113380   UNIT      192.168.137.1                   8E96E6A577A5                           020241009173113380   20241009173115235592  0  00        000       NAZAP0002                                                        EN                                                                                                                                             N00000425                     30normal process success.                                                                                                                                                                                                                                                                                                                                                                                         00D00000050                     0000       1     140000W00065@@";
        SAC04V127UOutVo outVo = SAC04V127UOutVo.builder().build();
        TelegramUserDataOutput<SAC04V127UOutVo> output = InterfaceTelegramTest.response(response, outVo);

        assertNotNull(output, "The request shoul not be null");
        assertInstanceOf(SAC04V127UOutVo.class, output.getOutput(), "Excepted an instance of SAC04V127UOutVo");

    }

    @Test
    @DisplayName("Response throw Exception")
    public void responseException() {
        String response = "abc";
        SAC04V127UOutVo outVo = SAC04V127UOutVo.builder().build();

        assertThatThrownBy(() -> InterfaceTelegramTest.response(response, outVo))
            .isInstanceOf(java.lang.ArrayIndexOutOfBoundsException.class)
            .hasMessageContaining("out of bounds");
    }

}