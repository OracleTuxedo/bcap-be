package maas.bcap.module.mc.mc04.smc04v041u;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SMC04V041U {

    private static final Logger logger = LogManager.getLogger(SMC04V041U.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMC04V041UOutVo> call(HttpServletRequest request, SMC04V041UInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC04V041UOutVo> result = TelegramUserDataOutput.<SMC04V041UOutVo>builder().build();

        SMC04V041UOutVo outVo = SMC04V041UOutVo.builder().build();

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
