package maas.bcap.module.ed.ed03.sed03f219r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SED03F219R implements BaseModuleInterface<SED03F219ROutVo, SED03F219RInVo> {
    private static final Logger logger = LogManager.getLogger(maas.bcap.module.ed.ed03.sed03f219r.SED03F219R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SED03F219ROutVo> call(HttpServletRequest request, SED03F219RInVo inVo, String screenId) {
        TelegramUserDataOutput<SED03F219ROutVo> result = TelegramUserDataOutput.<SED03F219ROutVo>builder().build();
        SED03F219ROutVo outVo = SED03F219ROutVo.builder().build();

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
