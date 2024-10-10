package maas.bcap.module.ac.ac04.sac04v228r;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.ac.ac04.sac04v228r.SAC04V228R;
import maas.bcap.module.ac.ac04.sac04v228r.SAC04V228RInVo;
import maas.bcap.module.ac.ac04.sac04v228r.SAC04V228ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAC04V228R {
    private static final Logger logger = LogManager.getLogger(SAC04V228R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SAC04V228ROutVo> call(HttpServletRequest request, SAC04V228RInVo inVo, String screenId) {
        TelegramUserDataOutput<SAC04V228ROutVo> result = TelegramUserDataOutput.<SAC04V228ROutVo>builder().build();
        SAC04V228ROutVo outVo = SAC04V228ROutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
//            String response = "00001180devaps01202410101109470014257200SAC04V228R              MTI R                        devaps0120241010110947001425720020241010110947899   UNIT      192.168.1.16                    088FC37E596F                           020241010110947899   20241010110947385306  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000243                            155057     MSR_40001_MID_71000029413_402_20210701_20210701_1561.csv                                                                                                                                                1   @@";
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

