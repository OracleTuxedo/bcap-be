package maas.bcap.module.au.au01.sau01f740r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;

import maas.bcap.module.ac.ac01.SAC01F995RInVo;
import maas.bcap.module.ac.ac01.SAC01F995ROutVo;
import maas.bcap.module.au.au01.sau01f730r.SAU01F730RInVo;
import maas.bcap.module.au.au01.sau01f730r.SAU01F730ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SAU01F740R {
    private static final Logger logger = LogManager.getLogger(maas.bcap.module.au.au01.sau01f740r.SAU01F740R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAU01F740ROutVo> call(HttpServletRequest request, SAU01F740RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAU01F740ROutVo> result = TelegramUserDataOutput.<SAU01F740ROutVo>builder().build();
        SAU01F740ROutVo outVo = SAU01F740ROutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
        } catch (TelegramNestedRuntimeException e) {
            logger.info(e.toString());
            logger.info(e.getMsg());
        } catch (Exception e) {
            logger.info(e.toString());
            logger.info(e.getClass());
            logger.info(e.getLocalizedMessage());
            logger.info(e.getMessage());
        }

        return result;
    }
}

