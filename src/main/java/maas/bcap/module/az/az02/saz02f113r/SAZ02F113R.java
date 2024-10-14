package maas.bcap.module.az.az02.saz02f113r;

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
public class SAZ02F113R implements BaseModuleInterface<SAZ02F113ROutVo, SAZ02F113RInVo> {
    private static final Logger logger = LogManager.getLogger(SAZ02F113R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ02F113ROutVo> call(HttpServletRequest request, SAZ02F113RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ02F113ROutVo> result = TelegramUserDataOutput.<SAZ02F113ROutVo>builder().build();
        SAZ02F113ROutVo outVo = SAZ02F113ROutVo.builder().build();

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
