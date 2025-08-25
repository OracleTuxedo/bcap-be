package maas.bcap.module.ac.ac01;

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
public class SAC01F995R {
    private static final Logger log = LogManager.getLogger(SAC01F995R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAC01F995ROutVo> call(HttpServletRequest request, SAC01F995RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC01F995ROutVo> result = TelegramUserDataOutput.<SAC01F995ROutVo>builder().build();
        SAC01F995ROutVo outVo = SAC01F995ROutVo.builder().build();

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
