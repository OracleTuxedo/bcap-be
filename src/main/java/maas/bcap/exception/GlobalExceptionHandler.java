package maas.bcap.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.Date;

@Order
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(value = ApiRequestException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiResponse testhandler(RuntimeException ex) {
        return new ApiResponse(HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            "Resource Not Found");
    }

}





//    @ExceptionHandler(ApiRequestException.class)
//    public ApiResponse<Object> handleGeneralException
//        (Exception ex, HttpServletRequest request){
//        return ResponseUtil.error(Collections.singletonList(ex.getMessage()),
//            "An unexpected error occured", 1001, request.getRequestURI());
//    }