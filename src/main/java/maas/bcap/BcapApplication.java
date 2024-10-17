package maas.bcap;

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
            logger.info("Start Application");
        } catch (Exception e) {
            logger.info("Application Failed !!!");
            throw new RuntimeException(e);
        }

    }

}
