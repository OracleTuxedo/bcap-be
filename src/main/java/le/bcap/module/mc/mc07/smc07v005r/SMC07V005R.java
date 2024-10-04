package le.bcap.module.mc.mc07.smc07v005r;

import jakarta.servlet.http.HttpServletRequest;
import le.bcap.az.ServiceSupport;
import le.bcap.module.BaseModuleInterface;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class SMC07V005R implements BaseModuleInterface<SMC07V005ROutVo, SMC07V005RInVo> {
    private static final Logger logger = LogManager.getLogger(SMC07V005R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SMC07V005ROutVo> call(HttpServletRequest request, SMC07V005RInVo inVo, String screenId) {
        TelegramUserDataOutput<SMC07V005ROutVo> result = TelegramUserDataOutput.<SMC07V005ROutVo>builder().build();

        SMC07V005ROutVo outVo = SMC07V005ROutVo.builder().build();

        try{
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
        } catch (TelegramNestedRuntimeException e){
            logger.info(e.toString());
            logger.info(e.getMsg());
        } catch (Exception e){
            logger.info(e.toString());
            logger.info(e.getClass());
            logger.info(e.getLocalizedMessage());
            logger.info(e.getMessage());
        }

        return result;
    }
}
