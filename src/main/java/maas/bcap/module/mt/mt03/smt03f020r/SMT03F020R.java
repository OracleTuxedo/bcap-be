package maas.bcap.module.mt.mt03.smt03f020r;


import javax.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class SMT03F020R {
    private static final Logger log = LogManager.getLogger(SMT03F020R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMT03F020ROutVo> call(HttpServletRequest request, SMT03F020RInVo inVo, String screenId) {
        TelegramUserDataOutput<SMT03F020ROutVo> result = TelegramUserDataOutput.<SMT03F020ROutVo>builder().build();
        SMT03F020ROutVo outVo = SMT03F020ROutVo.builder().build();

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
