package maas.bcap.module.az.az05.saz05f041u;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.common.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SAZ05F041U {
    private static final Logger logger = LogManager.getLogger(SAZ05F041U.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAZ05F041UOutVo> call(HttpServletRequest request, SAZ05F041UInVo inVo, String screenId) {
        TelegramUserDataOutput<SAZ05F041UOutVo> result = TelegramUserDataOutput.<SAZ05F041UOutVo>builder().build();
        SAZ05F041UOutVo outVo = SAZ05F041UOutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
//            String response = "00000968devaps01202410141253330024257300SAZ05F041U              MTI R                        devaps0120241014125333002425730020241014125332614   UNIT      192.168.1.16                    088FC37E596F                           020241014125332614   20241014125333186110  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000031                              1@@";
//            result = InterfaceTelegramTest.testCall(inputUserData, inVo, outVo, response);
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
