package maas.bcap.module.ac.ac06.sac06v610r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.ac.ac06.sac06v610r.SAC06V610R;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAC06V610R {
    private static final Logger logger = LogManager.getLogger(SAC06V610R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAC06V610ROutVo> call(HttpServletRequest request, SAC06V610RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC06V610ROutVo> result = TelegramUserDataOutput.<SAC06V610ROutVo>builder().build();
        SAC06V610ROutVo outVo = SAC06V610ROutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
//            String response = "00001163devaps01202410101338380044256900SAC06V610R              MTI R                        devaps0120241010133838004425690020241010133838576   UNIT      192.168.1.16                    088FC37E596F                           020241010133838576   20241010133838297945  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000625                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             02inquiry process success.                                                                            The Transaction Successfully Ended.                                                                 D00000026                     00009@@";
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
