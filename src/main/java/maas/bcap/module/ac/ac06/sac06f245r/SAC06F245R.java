package maas.bcap.module.ac.ac06.sac06f245r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.ac.ac06.sac06f245r.SAC06F245R;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAC06F245R {
    private static final Logger logger = LogManager.getLogger(SAC06F245R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAC06F245ROutVo> call(HttpServletRequest request, SAC06F245RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC06F245ROutVo> result = TelegramUserDataOutput.<SAC06F245ROutVo>builder().build();
        SAC06F245ROutVo outVo = SAC06F245ROutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
//            String response = "00000968devaps01202410101324540034257300SAC06F245R              MTI R                        devaps0120241010132454003425730020241010132454763   UNIT      192.168.1.16                    088FC37E596F                           020241010132454763   20241010132454296852  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000031                     0000000912@@";
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
