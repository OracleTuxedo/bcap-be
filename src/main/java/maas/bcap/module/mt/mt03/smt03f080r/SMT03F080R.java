package maas.bcap.module.mt.mt03.smt03f080r;

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
public class SMT03F080R {
    private static final Logger log = LogManager.getLogger(SMT03F080R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMT03F080ROutVo> call(HttpServletRequest request, SMT03F080RInVo inVo, String screenId) {
        TelegramUserDataOutput<SMT03F080ROutVo> result = TelegramUserDataOutput.<SMT03F080ROutVo>builder().build();

        SMT03F080ROutVo outVo = SMT03F080ROutVo.builder().build();

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
