package maas.bcap.module.mc.mc02.smc02v051u;

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
public class SMC02V051U {
    private static final Logger log = LogManager.getLogger(SMC02V051U.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMC02V051UOutVo> call(HttpServletRequest request, SMC02V051UInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC02V051UOutVo> result = TelegramUserDataOutput.<SMC02V051UOutVo>builder().build();
        SMC02V051UOutVo outVo = SMC02V051UOutVo.builder().build();

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
