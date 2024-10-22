package maas.bcap.module.az.az03;

import maas.bcap.module.az.az03.saz03v701u.SAZ03V701UInVo;
import maas.bcap.module.az.az03.saz03v701u.SAZ03V701UOutVo;
import mti.com.cipher.SHAEncryption;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

class SAZ03V701UTest {

    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {
        SAZ03V701UInVo saz03v701uInVo = SAZ03V701UInVo.builder()
            .usr_id("1787130271")
            .usr_paswd("7DB3C8AE5015726FB1B0120DCA9C6C7452279219553DCF4818C4DF885164463A")
            .admin_yn("N")
            .chnl_clcd("1")
            .req_tp("I")
            .build();

        String request = InterfaceTelegramTest.request("SAZ03V701U", saz03v701uInVo);

        Assertions.assertNotNull(request, "The request should not be null");
        Assertions.assertFalse(request.isEmpty(), "The request should not be empyt");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00001070devaps01202410221334230014256400SAZ03V701U              MTI R                        devaps0120241022133423001425640020241022133423036   UNIT      192.168.1.3                     581CF8933F96            1787130271     020241022133423036   20241022133423725174  0  00        000       IAZAP0000                                                        EN                                                                                                                                             N00000425                     30Login success.                                                                                                                                                                                                                                                                                                                                                                                                  00D00000133                     1787130271     Yosua Sutandar                                    N1787130271                                 10Y@@";
        SAZ03V701UOutVo saz03v701uOutVo = SAZ03V701UOutVo.builder().build();
        TelegramUserDataOutput<SAZ03V701UOutVo> output = InterfaceTelegramTest.response(response, saz03v701uOutVo);

        Assertions.assertNotNull(output, "The request should not be null");
        Assertions.assertInstanceOf(SAZ03V701UOutVo.class, output.getOutput(), "Expected an instance of SED03F209ROutVo");
    }

    @Test
    @DisplayName("SHA Algorithm")
    public void encrypt1() throws NoSuchAlgorithmException {
        String userId = "1787130271";
        String password = "@Yokkemti";

        String value = "7DB3C8AE5015726FB1B0120DCA9C6C7452279219553DCF4818C4DF885164463A";

        String result = SHAEncryption.encrypt(userId + password);

        System.out.println(value);
        System.out.println(result);

        Assertions.assertEquals(value, result);
    }

    @Test
    @DisplayName("SHA Algorithm")
    public void encryp2t() throws NoSuchAlgorithmException {
        String userId = "2197130504";
        String password = "2197130504y";

        String value = "1A63C9D2E66C3D5D20380BD2832F00B8F9B68CD94A4DA95DC56427D8E08D9FF1";

        String result = SHAEncryption.encrypt(userId + password);

        System.out.println(value);
        System.out.println(result);

        Assertions.assertEquals(value, result);
    }
}