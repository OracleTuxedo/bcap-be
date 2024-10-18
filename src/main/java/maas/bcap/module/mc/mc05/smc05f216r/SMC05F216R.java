package maas.bcap.module.mc.mc05.smc05f216r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMC05F216R {
    private static final Logger logger = LogManager.getLogger(SMC05F216R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMC05F216ROutVo> call(HttpServletRequest request, SMC05F216RInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC05F216ROutVo> result = TelegramUserDataOutput.<SMC05F216ROutVo>builder().build();

        SMC05F216ROutVo outVo = SMC05F216ROutVo.builder().build();

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
