package maas.bcap.module.mt.mt03.smt03f010r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.mt.mt01.smt01f009c.SMT01F009CInVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMT03F010R {
    private static final Logger logger = LogManager.getLogger(SMT03F010R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMT03F010ROutVo> call(HttpServletRequest request, SMT01F009CInVo inVo, String screenId) {
        TelegramUserDataOutput<SMT03F010ROutVo> result = TelegramUserDataOutput.<SMT03F010ROutVo>builder().build();
        SMT03F010ROutVo outVo = SMT03F010ROutVo.builder().build();

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
