package maas.bcap.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.example.dto.ExampleInDto;
import maas.bcap.example.dto.ExampleOutDto;
import maas.bcap.example.service.ExampleService;
import maas.bcap.exception.ApiRequestException;
import maas.bcap.exception.ApiResponse;
import maas.bcap.exception.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping; //Tambahan untuk Error Handling
import org.springframework.http.HttpStatus; //Error di Global Exception Handler dan Logging
import org.springframework.http.ResponseEntity; //Error di Global Exception Handler dan Logging
import org.springframework.web.bind.annotation.ExceptionHandler; //Error di Global Exception Handler dan Logging

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    //private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

//    @PostMapping("/list-of-edc")
//    public ExampleOutDto getListOfEDC(HttpServletRequest request, @RequestBody ExampleInDto inDto) throws Exception {
//        return exampleService.getListOfEDC(request, inDto, "ED999");
//    }
//
//    @GetMapping("/error")
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
//        return new ResponseEntity<>("Invalid request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//    }

//    @PostMapping("/hah")
//    public ResponseEntity<ApiResponse<ExampleInDto>> test(HttpServletRequest request, @RequestBody ExampleInDto inDto)
//        throws Exception {
//        if(!Objects.equals(inDto.getPrd_tp_cd(), "EDC"))
//        {
//            String[] errors = {"123", String.valueOf(123)};
//
//            return ResponseEntity.ok(ResponseUtil.error(List.of(errors), "Data Service not Accepted", 400, request.getRequestURI()));
//        }
//        return ResponseEntity.ok(ResponseUtil.success(inDto, "Data Accepted successfully", request.getRequestURI()));
//    }

    @PostMapping("/hah")
    public ExampleInDto test(HttpServletRequest request, @RequestBody ExampleInDto inDto) throws Exception {
        if (!Objects.equals(inDto.getPrd_tp_cd(), "EDC")) {
            throw new ApiRequestException("404");
        }

        return inDto;
//
//    @GetMapping("/errorbypast")
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception e) {
//        return new ResponseEntity<>("An internal error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @GetMapping("/example")
//    public String example() {
//        try {
//            // Simulasi error
//            throw new IllegalArgumentException("Invalid argument");
//        } catch (IllegalArgumentException e) {
//            logger.error("Error Error message: ", e);  // Logging the error
//            throw e;
       }
    }




