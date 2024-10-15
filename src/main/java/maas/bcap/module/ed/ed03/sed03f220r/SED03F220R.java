package maas.bcap.module.ed.ed03.sed03f220r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.ed.ed03.sed03f219r.SED03F219RInVo;
import maas.bcap.module.ed.ed03.sed03f219r.SED03F219ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SED03F220R implements BaseModuleInterface<SED03F220ROutVo, SED03F220RInVo>

    {
        private static final Logger logger = LogManager.getLogger(maas.bcap.module.ed.ed03.sed03f220r.SED03F220R.class);

        @Autowired
        private ServiceSupport support;

        @Override
        public TelegramUserDataOutput<SED03F220ROutVo> call(HttpServletRequest request, SED03F220RInVo inVo, String screenId) {
        TelegramUserDataOutput<SED03F220ROutVo> result = TelegramUserDataOutput.<SED03F220ROutVo>builder().build();
        SED03F220ROutVo outVo = SED03F220ROutVo.builder().build();

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

