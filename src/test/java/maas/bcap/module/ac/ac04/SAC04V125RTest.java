package maas.bcap.module.ac.ac04;

import maas.bcap.module.ac.ac04.sac04v125r.SAC04V125RInVo;
import maas.bcap.module.ac.ac04.sac04v125r.SAC04V125ROutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SAC04V125RTest {

    @Test
    @DisplayName("InVo to Request")
    public void request() throws Exception {
        SAC04V125RInVo inVo = SAC04V125RInVo.builder()
            .inqr_strt_date("20220901")
            .inqr_end_date("20221001")
            .pmt_incap_proc_stat_cd("1")
            .page_size(20)
            .trx_type("1")
            .build();
        String request = InterfaceTelegramTest.request("SAC04V125R", inVo);
        assertNotNull(request, "The request should not be null");
        assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00002460devaps01202410111704140034260400SAC04V125R              MTI R                        devaps0120241011170414003426040020241011170413271   UNIT      192.168.137.1                   8E96E6A577A5                           020241011170413271   20241011170414961760  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000425                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             00D00001523                         20                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           27100003073420220911118800342579               70000        TEST ANDROID BY ANDREW   Jakarta BaratID                                                            Success   001Account number not found      200                                                                                                                                                                                                        0         Paid       73001583V  20210712202209111360202107132400000376                  17100003073420220922203300344718                5000        EDC ANDROID                                                                                         Success   001Account number not found      200                                                                                                                                                                                                        0         Paid       73001581V  20220202202209221360202202032400000437                  1@@";
        SAC04V125ROutVo outVo = SAC04V125ROutVo.builder().build();
        TelegramUserDataOutput<SAC04V125ROutVo> output = InterfaceTelegramTest.response(response, outVo);

        assertNotNull(output, "The request shoul not be null");
        assertInstanceOf(SAC04V125ROutVo.class, output.getOutput(), "Excepted an instance of SAC04V125ROutVo");

    }

    @Test
    @DisplayName("Response throw Exception")
    public void responseException() {
        String response = "abc";
        SAC04V125ROutVo outVo = SAC04V125ROutVo.builder().build();

        assertThatThrownBy(() -> InterfaceTelegramTest.response(response, outVo))
            .isInstanceOf(java.lang.ArrayIndexOutOfBoundsException.class)
            .hasMessageContaining("out of bounds");
    }

}