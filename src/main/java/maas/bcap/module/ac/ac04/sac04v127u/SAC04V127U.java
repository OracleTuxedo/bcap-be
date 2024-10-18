package maas.bcap.module.ac.ac04.sac04v127u;

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
public class SAC04V127U implements BaseModuleInterface<SAC04V127UOutVo, SAC04V127UInVo> {
    private static final Logger logger = LogManager.getLogger(SAC04V127U.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAC04V127UOutVo> call(HttpServletRequest request, SAC04V127UInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC04V127UOutVo> result = TelegramUserDataOutput.<SAC04V127UOutVo>builder().build();
        SAC04V127UOutVo outVo = SAC04V127UOutVo.builder().build();

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
