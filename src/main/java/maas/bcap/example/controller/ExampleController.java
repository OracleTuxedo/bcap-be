package maas.bcap.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.example.dto.ExampleInDto;
import maas.bcap.example.dto.ExampleOutDto;
import maas.bcap.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @PostMapping("/list-of-edc")
    public ExampleOutDto getListOfEDC(HttpServletRequest request, @RequestBody ExampleInDto inDto) throws Exception {
        return exampleService.getListOfEDC(request, inDto, "ED999");
    }
}
