package maas.bcap.module.ed.ed03.sed03f081r;

import javax.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import maas.bcap.module.BaseModuleInterface;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SED03F081R implements BaseModuleInterface<SED03F081ROutVo, SED03F081RInVo> {
    private static final Logger log = LogManager.getLogger(SED03F081R.class);

    @Autowired
    private ServiceSupport support;

    @Override
    public TelegramUserDataOutput<SED03F081ROutVo> call(HttpServletRequest request, SED03F081RInVo inVo, String screenId) {
        TelegramUserDataOutput<SED03F081ROutVo> result = TelegramUserDataOutput.<SED03F081ROutVo>builder().build();
        SED03F081ROutVo outVo = SED03F081ROutVo.builder().build();

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

