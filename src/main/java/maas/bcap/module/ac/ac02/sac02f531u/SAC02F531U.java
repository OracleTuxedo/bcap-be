package maas.bcap.module.ac.ac02.sac02f531u;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAC02F531U {
    private static final Logger logger = LogManager.getLogger(SAC02F531U.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAC02F531UOutVo> call(HttpServletRequest request, SAC02F531UInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC02F531UOutVo> result = TelegramUserDataOutput.<SAC02F531UOutVo>builder().build();
        SAC02F531UOutVo outVo = SAC02F531UOutVo.builder().build();

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
