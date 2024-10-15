package maas.bcap.module.ac.ac02;

import maas.bcap.module.ac.ac02.sac02f452r.SAC02F452RInVo;
import maas.bcap.module.ac.ac02.sac02f452r.SAC02F452ROutVo;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SAC02F452RTest {

    @Test
    @DisplayName("InVo To Request")
    public void request() throws Exception {
        SAC02F452RInVo sac02f452rInVo = SAC02F452RInVo.builder()
            .page_no(1)
            .page_size(10)
            .mid("71000204442")
            .auth_strt_date("20240101")
            .auth_end_date("20240919")
            .build();
        String request = InterfaceTelegramTest.request("SAC02F452R", sac02f452rInVo);

        assertNotNull(request, "The request should not be null");
        assertFalse(request.isEmpty(), "The request should not be empty");
    }

    @Test
    @DisplayName("Response To OutVo")
    public void response() throws Exception {
        String response = "00002606devaps01202410020933300017498100SAC02F452R              MTI R                        devaps0120241002093330001749810020241002093330751   UNIT      192.168.1.89                    0CDD2494CF5F                           020241002093330751   20241002093348307790  0  00        000       NAZAP0001                                                        EN                                                                                                                                             N00000425                     30inquiry process success.                                                                                                                                                                                                                                                                                                                                                                                        00D00001669                             96      1020240101202401054259450300373427   6451380000000000000006589000000000000000000005    00000000000000006589000000000000000000000000000000000000000000000000000000000N20240102202401035379408870000145   1113100000000000000006000000000000000000000015    00000000000000006000000000000000000000000000000000000000000000000000000000000N20240105202401094215708100006338   0000890000000000000115000000000000000000000005    00000000000000105000000000000000000000000000000000000000000000000000000000000 20240108202401094215708100041160   2693760000000000000020000000000000000000000005    00000000000000020000000000000000000000000000000000000000000000000000000000000N20240108202401094215708100041160   2536030000000000000025000000000000000000000005    00000000000000025000000000000000000000000000000000000000000000000000000000000N20240221202402224485580000080033   1129930000000000000006535000000000000000000005    00000000000000006535000000000000000000000000000000000000000000000000000000000N20240221202402224485580000080033   1129920000000000000002305000000000000000000005    00000000000000002305000000000000000000000000000000000000000000000000000000000N20240223202402254485580000080033   1111510000000000000006250000000000000000000005    00000000000000006250000000000000000000000000000000000000000000000000000000000N20240223202402254485580000080033   1111500000000000000003200000000000000000000005    00000000000000003200000000000000000000000000000000000000000000000000000000000N20240227202402294485580000080033   1112060000000000000006589000000000000000000005    00000000000000006589000000000000000000000000000000000000000000000000000000000N@@";
        SAC02F452ROutVo outVo = SAC02F452ROutVo.builder().build();
        TelegramUserDataOutput<SAC02F452ROutVo> output = InterfaceTelegramTest.response(response, outVo);

        assertNotNull(output, "The request should not be null");
        assertInstanceOf(SAC02F452ROutVo.class, output.getOutput(), "Expected an instance of SED03F209ROutVo");
    }

    @Test
    @DisplayName("Response Throw Exception")
    public void responseException() {
        String response = "aaa";
        SAC02F452ROutVo outVo = SAC02F452ROutVo.builder().build();

        assertThatThrownBy(() -> InterfaceTelegramTest.response(response, outVo))
            .isInstanceOf(java.lang.ArrayIndexOutOfBoundsException.class)
            .hasMessageContaining("out of bounds");

    }
}
