package maas.bcap.screen.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.screen.example.dto.ExampleInDto;
import maas.bcap.screen.example.dto.ExampleOutDto;
import maas.bcap.screen.example.dto.LoginInDto;
import maas.bcap.screen.example.dto.LoginOutDto;
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
        /// Example of Log Level
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
        /// Example of Routing Appender based specific class and Rolling File and Routing Appender under maas.bcap.screen package
        ThreadContext.put("className", "ExampleController");
        log.info("Calling remote API from ExampleController");
        ThreadContext.clearMap();
        return "111";
    }

    @PostMapping("/login")
    public LoginOutDto login(HttpServletRequest request, @RequestBody LoginInDto inDto) throws Exception {
        return exampleService.login(request, inDto, "WAZ030102H");
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) throws Exception {
        exampleService.logout(request, "WAZ030100H");
        return;
    }

}
