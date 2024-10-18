package maas.bcap.module.mc.mc04.smc04v042r;
import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.mc.mc02.smc02f042r.SMC02F042ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SMC04V042R implements BaseModuleInterface<SMC02F042ROutVo, SMC04V042RInVo> {
    private static final Logger logger = LogManager.getLogger(SMC04V042R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SMC02F042ROutVo> call(HttpServletRequest request, SMC04V042RInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC02F042ROutVo> result = TelegramUserDataOutput.<SMC02F042ROutVo>builder().build();
        SMC02F042ROutVo outVo = SMC02F042ROutVo.builder().build();

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
