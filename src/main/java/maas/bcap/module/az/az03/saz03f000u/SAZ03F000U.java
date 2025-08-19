package maas.bcap.module.az.az03.saz03f000u;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SAZ03F000U implements BaseModuleInterface<SAZ03F000UOutVo, SAZ03F000UInVo> {
    private static final Logger log = LogManager.getLogger(SAZ03F000U.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ03F000UOutVo> call(HttpServletRequest request, SAZ03F000UInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ03F000UOutVo> result = TelegramUserDataOutput.<SAZ03F000UOutVo>builder().build();
        SAZ03F000UOutVo outVo = SAZ03F000UOutVo.builder().build();

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


