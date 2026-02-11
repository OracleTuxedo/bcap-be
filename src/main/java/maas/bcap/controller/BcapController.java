package maas.bcap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("")
public class BcapController {

    @GetMapping("")
    public String app() {
        return "Version 2.5.1";
    }

}
