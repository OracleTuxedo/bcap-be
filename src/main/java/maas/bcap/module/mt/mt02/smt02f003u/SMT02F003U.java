package maas.bcap.module.mt.mt02.smt02f003u;

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
public class SMT02F003U implements BaseModuleInterface<SMT02F003UOutVo, SMT02F003UInVo> {
    private static final Logger log = LogManager.getLogger(SMT02F003U.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SMT02F003UOutVo> call(HttpServletRequest request, SMT02F003UInVo inVo, String screenId) {
        TelegramUserDataOutput<SMT02F003UOutVo> result = TelegramUserDataOutput.<SMT02F003UOutVo>builder().build();
        SMT02F003UOutVo outVo = SMT02F003UOutVo.builder().build();

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