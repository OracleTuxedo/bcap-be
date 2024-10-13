package maas.bcap.module.mt.mt02.smt02f002c;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.mt.mt02.smt02f002c.SMT02F002CInVo;
import maas.bcap.module.mt.mt02.smt02f002c.SMT02F002COutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMT02F002C implements BaseModuleInterface<SMT02F002COutVo, SMT02F002CInVo> {
    private static final Logger logger = LogManager.getLogger(SMT02F002C.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SMT02F002COutVo> call(HttpServletRequest request, SMT02F002CInVo inVo, String screenId) {
        TelegramUserDataOutput<SMT02F002COutVo> result = TelegramUserDataOutput.<SMT02F002COutVo>builder().build();
        SMT02F002COutVo outVo = SMT02F002COutVo.builder().build();

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