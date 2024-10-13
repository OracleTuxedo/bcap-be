package maas.bcap.module.mt.mt01.smt01f009c;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.az.az01.saz01f111r.SAZ01F111RInVo;
import maas.bcap.module.az.az01.saz01f111r.SAZ01F111ROutVo;
import maas.bcap.module.mt.mt01.smt01f009c.SMT01F009CInVo;
import maas.bcap.module.mt.mt01.smt01f009c.SMT01F009COutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMT01F009C implements BaseModuleInterface<SMT01F009COutVo, SMT01F009CInVo> {
    private static final Logger logger = LogManager.getLogger(SMT01F009C.class);

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