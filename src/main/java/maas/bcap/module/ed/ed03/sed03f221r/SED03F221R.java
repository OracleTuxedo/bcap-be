package maas.bcap.module.ed.ed03.sed03f221r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.ed.ed03.sed03f221r.SED03F221RInVo;
import maas.bcap.module.ed.ed03.sed03f221r.SED03F221ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SED03F221R implements BaseModuleInterface<SED03F221ROutVo, SED03F221RInVo>

{
    private static final Logger logger = LogManager.getLogger(maas.bcap.module.ed.ed03.sed03f220r.SED03F220R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SED03F221ROutVo> call(HttpServletRequest request, SED03F221RInVo inVo, String screenId) {
        TelegramUserDataOutput<SED03F221ROutVo> result = TelegramUserDataOutput.<SED03F221ROutVo>builder().build();
        SED03F221ROutVo outVo = SED03F221ROutVo.builder().build();

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

