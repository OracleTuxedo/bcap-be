package maas.bcap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("")
public class BcapController {
    private static final Logger log = LogManager.getLogger(BcapController.class);

    @GetMapping("")
    public String app() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
        return "Version 2.2";
    }

}
