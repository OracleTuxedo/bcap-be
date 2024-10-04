package maas.bcap.module.ed.ed03.sed03f075r;

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
public class SED03F075R implements BaseModuleInterface<SED03F075ROutVo, SED03F075RInVo> {
    private static final Logger logger = LogManager.getLogger(SED03F075R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SED03F075ROutVo> call(HttpServletRequest request, SED03F075RInVo inVo, String screenId) {
        TelegramUserDataOutput<SED03F075ROutVo> result = TelegramUserDataOutput.<SED03F075ROutVo>builder().build();
        SED03F075ROutVo outVo = SED03F075ROutVo.builder().build();

        try{
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
