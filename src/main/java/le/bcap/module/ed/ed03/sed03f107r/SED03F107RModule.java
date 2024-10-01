package le.bcap.module.ed.ed03.sed03f107r;

import jakarta.servlet.http.HttpServletRequest;
import le.bcap.az.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.util.InterfaceTelegramTest;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramOutputUserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SED03F107RModule {
    private static final Logger logger = LogManager.getLogger(SED03F107RModule.class);

    @Autowired
    private ServiceSupport support;

    public TelegramOutputUserData call(HttpServletRequest request, SED03F107RInVo inVo){
        TelegramOutputUserData result = TelegramOutputUserData.builder().build();
        SED03F107ROutVo outVo = SED03F107ROutVo.builder().build();

        try {
            TelegramInputUserData inputUserData = support.tuxedoHeader(request, "SED03F107", "WAC070100H");
//            result = serviceSupport.tuxedoTransaction(inputUserData, inVo, outVo);
            String response = "00001164devaps01202410011306560144232400SED03F107R              MTI R                        devaps0120241001130656014423240020241001130654985   UNIT      192.168.0.133                   0CDD2494CF5F                           020241001130654985   20241001130658326335  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000227                              10013000011976476    Z900                                                   040101E0016     33   EDC Android PAX A920                                                                                @@";
            result = InterfaceTelegramTest.testCall(inputUserData, inVo, outVo, response);
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
//Example Request
//SED03F107RInVo sed03F107RInVo = SED03F107RInVo.builder()
//        .prd_tp_cd("EDC")
//        .sno("0013000011976476")
//        .build();

//Request
//00000578                                SED03F107R              MTI S                                                                            UNIT      192.168.0.133                   0CDD2494CF5F                           020241001130654985                         00000      00000                                                                        EN                                                                                                                                              00000000                     EDC  0013000011976476

//Response
//00001164devaps01202410011306560144232400SED03F107R              MTI R                        devaps0120241001130656014423240020241001130654985   UNIT      192.168.0.133                   0CDD2494CF5F                           020241001130654985   20241001130658326335  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                 0D00000227                              10013000011976476    Z900                                                   040101E0016     33   EDC Android PAX A920                                                                                @@
//Response Edited
//00001164devaps01202410011306560144232400SED03F107R              MTI R                        devaps0120241001130656014423240020241001130654985   UNIT      192.168.0.133                   0CDD2494CF5F                           020241001130654985   20241001130658326335  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000227                              10013000011976476    Z900                                                   040101E0016     33   EDC Android PAX A920                                                                                @@