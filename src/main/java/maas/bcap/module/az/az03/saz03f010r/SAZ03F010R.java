package maas.bcap.module.az.az03.saz03f010r;

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
public class SAZ03F010R implements BaseModuleInterface<SAZ03F010ROutVo, SAZ03F010RInVo> {
    private static final Logger log = LogManager.getLogger(SAZ03F010R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ03F010ROutVo> call(HttpServletRequest request, SAZ03F010RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ03F010ROutVo> result = TelegramUserDataOutput.<SAZ03F010ROutVo>builder().build();
        SAZ03F010ROutVo outVo = SAZ03F010ROutVo.builder().build();

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
