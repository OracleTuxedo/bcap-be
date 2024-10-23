package maas.bcap.screen.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.screen.example.dto.ExampleInDto;
import maas.bcap.screen.example.dto.ExampleOutDto;
import maas.bcap.screen.example.service.ExampleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/example")
public class ExampleController {

    private static final Logger log = LogManager.getLogger(ExampleController.class);

    @Autowired
    private ExampleService exampleService;

    @PostMapping("/list-of-edc")
    public ExampleOutDto getListOfEDC(HttpServletRequest request, @RequestBody ExampleInDto inDto) throws Exception {
        return exampleService.getListOfEDC(request, inDto, "ED999");
    }

    @GetMapping("/test")
    public String getMethodName(HttpServletRequest request) {
        log.info("Hello World from Example Controller");

        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");

        return "Hello World";
    }

    @GetMapping("call-logging")
    public String callRemoteAPI() {
        ThreadContext.put("className", "ExampleController");
        log.info("Calling remote API from ExampleController");
        ThreadContext.clearMap();
        return "111";
    }

//    public static void main(String[] args) {
//        callRemoteAPI();
//    }
//
//    public static String test() {
//        log.info("Hello World from Example Controller");
//
//        log.trace("trace");
//        log.debug("debug");
//        log.info("info");
//        log.warn("warn");
//        log.error("error");
//        log.fatal("fatal");
//        return new String("Hello World");
//    }

}
