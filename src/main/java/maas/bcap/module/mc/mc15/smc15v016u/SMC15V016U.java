package maas.bcap.module.mc.mc15.smc15v016u;

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
public class SMC15V016U {
    private static final Logger logger = LogManager.getLogger(SMC15V016U.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMC15V016UOutVo> call(HttpServletRequest request, SMC15V016UInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC15V016UOutVo> result = TelegramUserDataOutput.<SMC15V016UOutVo>builder().build();
        SMC15V016UOutVo outVo = SMC15V016UOutVo.builder().build();

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
