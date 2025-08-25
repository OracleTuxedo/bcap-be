package maas.bcap.module.mt.mt01.smt01f009c;

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
public class SMT01F009C implements BaseModuleInterface<SMT01F009COutVo, SMT01F009CInVo> {
    private static final Logger log = LogManager.getLogger(SMT01F009C.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SMT01F009COutVo> call(HttpServletRequest request, SMT01F009CInVo inVo, String screenId) {
        TelegramUserDataOutput<SMT01F009COutVo> result = TelegramUserDataOutput.<SMT01F009COutVo>builder().build();
        SMT01F009COutVo outVo = SMT01F009COutVo.builder().build();

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