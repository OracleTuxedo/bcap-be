package maas.bcap.module.az.az03.saz03v701u;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAZ03V701U implements BaseModuleInterface<SAZ03V701UOutVo, SAZ03V701UInVo> {

    private static final Logger log = LogManager.getLogger(SAZ03V701U.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ03V701UOutVo> call(HttpServletRequest request, SAZ03V701UInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ03V701UOutVo> result = TelegramUserDataOutput.<SAZ03V701UOutVo>builder().build();
        SAZ03V701UOutVo outVo = SAZ03V701UOutVo.builder().build();

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
