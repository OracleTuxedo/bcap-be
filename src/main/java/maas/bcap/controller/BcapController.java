package maas.bcap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("")
public class BcapController {
    private static final Logger log = LogManager.getLogger(BcapController.class);

    @GetMapping("")
    public String getMethodName(@RequestParam String param) {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
        return new String("Version 2.0");
    }

}
