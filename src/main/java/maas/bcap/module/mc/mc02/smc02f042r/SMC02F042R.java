package maas.bcap.module.mc.mc02.smc02f042r;

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
public class SMC02F042R implements BaseModuleInterface<SMC02F042ROutVo, SMC02F042RInVo> {
    private static final Logger log = LogManager.getLogger(SMC02F042R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SMC02F042ROutVo> call(HttpServletRequest request, SMC02F042RInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC02F042ROutVo> result = TelegramUserDataOutput.<SMC02F042ROutVo>builder().build();
        SMC02F042ROutVo outVo = SMC02F042ROutVo.builder().build();

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
