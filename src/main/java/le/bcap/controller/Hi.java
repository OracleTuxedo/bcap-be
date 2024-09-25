package le.bcap.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Hi {
    @GetMapping("hi")
    public String hi() {
        return new String("Hello World");
    }
    
}
