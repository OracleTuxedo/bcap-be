package maas.bcap.module.az.az01.saz01f112r;

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
public class SAZ01F112R implements BaseModuleInterface<SAZ01F112ROutVo, SAZ01F112RInVo> {
    private static final Logger log = LogManager.getLogger(SAZ01F112R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ01F112ROutVo> call(HttpServletRequest request, SAZ01F112RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ01F112ROutVo> result = TelegramUserDataOutput.<SAZ01F112ROutVo>builder().build();
        SAZ01F112ROutVo outVo = SAZ01F112ROutVo.builder().build();

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
