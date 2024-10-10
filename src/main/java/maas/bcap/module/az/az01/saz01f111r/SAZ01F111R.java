package maas.bcap.module.az.az01.saz01f111r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAZ01F111R implements BaseModuleInterface<SAZ01F111ROutVo, SAZ01F111RInVo> {
    private static final Logger logger = LogManager.getLogger(SAZ01F111R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ01F111ROutVo> call(HttpServletRequest request, SAZ01F111RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ01F111ROutVo> result = TelegramUserDataOutput.<SAZ01F111ROutVo>builder().build();
        SAZ01F111ROutVo outVo = SAZ01F111ROutVo.builder().build();

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
