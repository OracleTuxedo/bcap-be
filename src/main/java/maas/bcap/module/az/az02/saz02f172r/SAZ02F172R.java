package maas.bcap.module.az.az02.saz02f172r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import maas.bcap.module.az.az02.saz02f132r.SAZ02F132RInVo;
import maas.bcap.module.az.az02.saz02f132r.SAZ02F132ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAZ02F172R implements BaseModuleInterface<SAZ02F132ROutVo, SAZ02F132RInVo> {
    private static final Logger logger = LogManager.getLogger(SAZ02F172R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SAZ02F132ROutVo> call(HttpServletRequest request, SAZ02F132RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ02F132ROutVo> result = TelegramUserDataOutput.<SAZ02F132ROutVo>builder().build();
        SAZ02F132ROutVo outVo = SAZ02F132ROutVo.builder().build();

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
