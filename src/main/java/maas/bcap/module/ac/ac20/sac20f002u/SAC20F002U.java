package maas.bcap.module.ac.ac20.sac20f002u;

import javax.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SAC20F002U {
    private static final Logger log = LogManager.getLogger(SAC20F002U.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAC20F002UOutVo> call(HttpServletRequest request, SAC20F002UInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC20F002UOutVo> result = TelegramUserDataOutput.<SAC20F002UOutVo>builder().build();
        SAC20F002UOutVo outVo = SAC20F002UOutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
//            String response = "";
//            result = InterfaceTelegramTest.testCall(inputUserData, inVo, outVo, response);
        } catch (TelegramNestedRuntimeException e) {
            log.info(e.toString());
            log.info(e.getMsg());
        } catch (Exception e) {
            log.info(e.toString());
            log.info(e.getClass());
            log.info(e.getLocalizedMessage());
            log.info(e.getMessage());
        }

        return result;
    }
}
