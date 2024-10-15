package maas.bcap.module.mc.mc05.smc05f112r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.mc.mc05.smc05f112r.SMC05F112R;
import maas.bcap.module.mc.mc05.smc05f112r.SMC05F112RInVo;
import maas.bcap.module.mc.mc05.smc05f112r.SMC05F112ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMC05F112R {
    private static final Logger logger = LogManager.getLogger(SMC05F112R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMC05F112ROutVo> call(HttpServletRequest request, SMC05F112RInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC05F112ROutVo> result = TelegramUserDataOutput.<SMC05F112ROutVo>builder().build();

        SMC05F112ROutVo outVo = SMC05F112ROutVo.builder().build();

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
