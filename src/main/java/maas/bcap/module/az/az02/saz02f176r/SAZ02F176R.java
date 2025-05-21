package maas.bcap.module.az.az02.saz02f176r;

import javax.servlet.http.HttpServletRequest;
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
public class SAZ02F176R implements BaseModuleInterface<SAZ02F176ROutVo, SAZ02F176RInVo> {
    private static final Logger log = LogManager.getLogger(SAZ02F176R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ02F176ROutVo> call(HttpServletRequest request, SAZ02F176RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ02F176ROutVo> result = TelegramUserDataOutput.<SAZ02F176ROutVo>builder().build();
        SAZ02F176ROutVo outVo = SAZ02F176ROutVo.builder().build();

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
