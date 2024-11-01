package maas.bcap.module.az.az02.saz02f112r;

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
public class SAZ02F112R implements BaseModuleInterface<SAZ02F112ROutVo, SAZ02F112RInVo> {
    private static final Logger log = LogManager.getLogger(SAZ02F112R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ02F112ROutVo> call(HttpServletRequest request, SAZ02F112RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ02F112ROutVo> result = TelegramUserDataOutput.<SAZ02F112ROutVo>builder().build();
        SAZ02F112ROutVo outVo = SAZ02F112ROutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
        } catch (TelegramNestedRuntimeException e) {
            log.info(e.toString());
            log.info(e.getMsg());
        } catch (Exception e) {
            log.info(e.toString());
            log.info(e.getClass());
            log.info(e.getLocalizedMessage());
            log.info(e.getMessage());
        }

        return result;
    }
}
