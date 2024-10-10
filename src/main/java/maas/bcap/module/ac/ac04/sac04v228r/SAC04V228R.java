package maas.bcap.module.ac.ac04.sac04v228r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.ac.ac04.sac04v228r.SAC04V228R;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAC04V228R {
    private static final Logger logger = LogManager.getLogger(SAC04V228R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAC04V228ROutVo> call(HttpServletRequest request, SAC04V228RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC04V228ROutVo> result = TelegramUserDataOutput.<SAC04V228ROutVo>builder().build();
        SAC04V228ROutVo outVo = SAC04V228ROutVo.builder().build();

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

