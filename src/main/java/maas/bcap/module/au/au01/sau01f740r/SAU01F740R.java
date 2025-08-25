package maas.bcap.module.au.au01.sau01f740r;

import javax.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SAU01F740R {
    private static final Logger log = LogManager.getLogger(maas.bcap.module.au.au01.sau01f740r.SAU01F740R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAU01F740ROutVo> call(HttpServletRequest request, SAU01F740RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAU01F740ROutVo> result = TelegramUserDataOutput.<SAU01F740ROutVo>builder().build();
        SAU01F740ROutVo outVo = SAU01F740ROutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
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

