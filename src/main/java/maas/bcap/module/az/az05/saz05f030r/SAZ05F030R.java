package maas.bcap.module.az.az05.saz05f030r;

import javax.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAZ05F030R {
    private static final Logger log = LogManager.getLogger(SAZ05F030R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAZ05F030ROutVo> call(HttpServletRequest request, SAZ05F030RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ05F030ROutVo> result = TelegramUserDataOutput.<SAZ05F030ROutVo>builder().build();

        SAZ05F030ROutVo outVo = SAZ05F030ROutVo.builder().build();

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
