package maas.bcap.module.az.az03.saz03f340u;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SAZ03F340U implements BaseModuleInterface<SAZ03F340UOutVo, SAZ03F340UInVo> {
    private static final Logger log = LogManager.getLogger(SAZ03F340U.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ03F340UOutVo> call(HttpServletRequest request, SAZ03F340UInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ03F340UOutVo> result = TelegramUserDataOutput.<SAZ03F340UOutVo>builder().build();
        SAZ03F340UOutVo outVo = SAZ03F340UOutVo.builder().build();

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


