package maas.bcap.module.mc.mc15.smc15v018u;

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
public class SMC15V018U {
    private static final Logger log = LogManager.getLogger(SMC15V018U.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMC15V018UOutVo> call(HttpServletRequest request, SMC15V018UInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC15V018UOutVo> result = TelegramUserDataOutput.<SMC15V018UOutVo>builder().build();
        SMC15V018UOutVo outVo = SMC15V018UOutVo.builder().build();

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
