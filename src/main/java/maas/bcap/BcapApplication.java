package maas.bcap;

import maas.bcap.craniumtest.*;
import mti.com.telegram.util.InterfaceTelegramTest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BcapApplication {

    private static final Logger logger = LogManager.getLogger(BcapApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BcapApplication.class, args);
        try {
            logger.atLevel(Level.ALL);
            logger.info("Le Rucco Start Application");

//            InterfaceTelegramTest.interfaceTuxedoParseRequest();
//            InterfaceTelegramTest.interfaceTuxedoParseResponse();

//            FalahTest.interfaceTuxedoParseRequest();
//            FalahTest.interfaceTuxedoParseResponse();

//            LeRuccoTest.interfaceTuxedoParseRequest();
//            LeRuccoTest.interfaceTuxedoParseResponse();

//            PanpanTest.interfaceTuxedoParseRequest();
//            PanpanTest.interfaceTuxedoParseResponse();

//            EndgTest.interfaceTuxedoParseRequest();
//            EndgTest.interfaceTuxedoParseResponse();

//           DarrenTest.interfaceTuxedoParseRequest();
//           DarrenTest.interfaceTuxedoParseResponse();

//            NeheTest.interfaceTuxedoParseRequest();
//            NeheTest.interfaceTuxedoParseResponse();


        } catch (Exception e) {
            logger.info("Le Rucco Failed MAYDAY MAYDAY MAYDAY");
            throw new RuntimeException(e);
        }

    }

}
