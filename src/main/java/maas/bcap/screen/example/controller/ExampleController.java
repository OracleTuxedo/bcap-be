package maas.bcap.screen.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.screen.example.dto.ExampleInDto;
import maas.bcap.screen.example.dto.ExampleOutDto;
import maas.bcap.screen.example.dto.LoginInDto;
import maas.bcap.screen.example.dto.LoginOutDto;
import maas.bcap.screen.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @PostMapping("/list-of-edc")
    public ExampleOutDto getListOfEDC(HttpServletRequest request, @RequestBody ExampleInDto inDto) throws Exception {
        return exampleService.getListOfEDC(request, inDto, "ED999");
    }

    @GetMapping("/test")
    public String getMethodName(HttpServletRequest request) {
        return new String("Hello World");
    }

    // @GetMapping("/login")
    // public LoginOutDto loginGet(HttpServletRequest request) throws Exception {
    // LoginInDto inDto = LoginInDto.builder()
    // .build();
    // return exampleService.login(request, inDto, "WAZ030102H");
    // }

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
