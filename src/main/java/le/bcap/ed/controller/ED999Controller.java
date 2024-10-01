package le.bcap.ed.controller;

import jakarta.servlet.http.HttpServletRequest;
import le.bcap.ed.dto.ED999InDto;
import le.bcap.ed.dto.ED999OutDto;
import le.bcap.ed.service.ED999Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ed")
public class ED999Controller {

    @Autowired
    private ED999Service ed999Service;

    @PostMapping("/list-of-edc")
    public Object getListOfEDC(HttpServletRequest request, @RequestBody ED999InDto inDto) throws Exception {
//        return inDto;
        return ed999Service.getListOfEDC(request, inDto);
    }
}
