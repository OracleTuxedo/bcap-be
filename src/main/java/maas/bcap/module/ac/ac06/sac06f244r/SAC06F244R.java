package maas.bcap.module.ac.ac06.sac06f244r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.ac.ac06.sac06f244r.SAC06F244R;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAC06F244R {
    private static final Logger logger = LogManager.getLogger(SAC06F244R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAC06F244ROutVo> call(HttpServletRequest request, SAC06F244RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC06F244ROutVo> result = TelegramUserDataOutput.<SAC06F244ROutVo>builder().build();
        SAC06F244ROutVo outVo = SAC06F244ROutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
//            String response = "00001489devaps01202410101150150024257200SAC06F244R              MTI R                        devaps0120241010115015002425720020241010115016306   UNIT      192.168.1.16                    088FC37E596F                           020241010115016306   20241010115015853959  0  00        000       NMCAP0001                                                        EN                                                                                                                                             N00000525                     30Inquiry has been completed.                                                                                                                                                                                                                                                                                                                                                                                     01Inquiry has been completed.                                                                         D00000452                                                                                                                                                                                                                                                                                          2       1000000001220170113coba                                                                                                1674110016   5                     20170113@@";
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

