package maas.bcap.module.ed.ed03.sed03f187r;


import javax.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SED03F187R implements BaseModuleInterface<SED03F187ROutVo, SED03F187RInVo> {
    private static final Logger log = LogManager.getLogger(maas.bcap.module.ed.ed03.sed03f187r.SED03F187R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SED03F187ROutVo> call(HttpServletRequest request, SED03F187RInVo inVo, String screenId) {
        TelegramUserDataOutput<SED03F187ROutVo> result = TelegramUserDataOutput.<SED03F187ROutVo>builder().build();
        SED03F187ROutVo outVo = SED03F187ROutVo.builder().build();

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