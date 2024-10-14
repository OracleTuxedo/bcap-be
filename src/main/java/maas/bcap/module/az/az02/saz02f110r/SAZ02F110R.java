package maas.bcap.module.az.az02.saz02f110r;

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
public class SAZ02F110R implements BaseModuleInterface<SAZ02F110ROutVo, SAZ02F110RInVo> {
    private static final Logger logger = LogManager.getLogger(SAZ02F110R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ02F110ROutVo> call(HttpServletRequest request, SAZ02F110RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ02F110ROutVo> result = TelegramUserDataOutput.<SAZ02F110ROutVo>builder().build();
        SAZ02F110ROutVo outVo = SAZ02F110ROutVo.builder().build();

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
