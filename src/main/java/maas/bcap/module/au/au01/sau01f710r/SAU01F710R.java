package maas.bcap.module.au.au01.sau01f710r;
import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;

import maas.bcap.module.ac.ac01.SAC01F995RInVo;
import maas.bcap.module.ac.ac01.SAC01F995ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SAU01F710R {
    private static final Logger logger = LogManager.getLogger(maas.bcap.module.au.au01.sau01f710r.SAU01F710R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAU01F710ROutVo> call(HttpServletRequest request, SAU01F710RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAU01F710ROutVo> result = TelegramUserDataOutput.<SAU01F710ROutVo>builder().build();
        SAU01F710ROutVo outVo = SAU01F710ROutVo.builder().build();

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
