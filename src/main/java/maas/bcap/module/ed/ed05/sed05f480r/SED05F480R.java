package maas.bcap.module.ed.ed05.sed05f480r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.ed.ed05.sed05f480r.SED05F480R;
import maas.bcap.module.ed.ed05.sed05f480r.SED05F480RInVo;
import maas.bcap.module.ed.ed05.sed05f480r.SED05F480ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SED05F480R {
    private static final Logger logger = LogManager.getLogger(SED05F480R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SED05F480ROutVo> call(HttpServletRequest request, SED05F480RInVo inVo, String screenId) {
        TelegramUserDataOutput<SED05F480ROutVo> result = TelegramUserDataOutput.<SED05F480ROutVo>builder().build();

        SED05F480ROutVo outVo = SED05F480ROutVo.builder().build();

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
