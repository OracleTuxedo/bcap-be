package maas.bcap.module.ed.ed03.sed03f120r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.ed.ed03.sed03f120r.SED03F120RInVo;
import maas.bcap.module.ed.ed03.sed03f120r.SED03F120ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SED03F120R implements BaseModuleInterface<SED03F120ROutVo, SED03F120RInVo> {
    private static final Logger logger = LogManager.getLogger(maas.bcap.module.ed.ed03.sed03f120r.SED03F120R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SED03F120ROutVo> call(HttpServletRequest request, SED03F120RInVo inVo, String screenId) {
        TelegramUserDataOutput<SED03F120ROutVo> result = TelegramUserDataOutput.<SED03F120ROutVo>builder().build();
        SED03F120ROutVo outVo = SED03F120ROutVo.builder().build();

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

