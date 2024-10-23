package maas.bcap.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse<T>
{

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

}




//public class ApiResponse<T>
//{
////    private boolean success;
////    private String message;
////    private T data;
////    private List<String> errors;
////    private int errorCode;
////    private long timestamp;
////    private String path;
//
//    private int statusCode;
//    private Date timestamp;
//    private String message;
//    private String description;
//
////    public ApiResponse() {
////
////    }
//
//    // Getters and Setters
//
//    // Getters and Setters
////
////    public boolean isSuccess() {
////        return success;
////    }
////
////    public void setSuccess(boolean success) {
////        this.success = success;
////    }
////
////    public String getMessage() {
////        return message;
////    }
////
////    public void setMessage(String message) {
////        this.message = message;
////    }
////
////    public T getData() {
////        return data;
////    }
////
////    public void setData(T data) {
////        this.data = data;
////    }
////
////    public List<String> getErrors() {
////        return errors;
////    }
////
////    public void setErrors(List<String> errors) {
////        this.errors = errors;
////    }
////
////    public int getErrorCode() {
////        return errorCode;
////    }
////
////    public void setErrorCode(int errorCode) {
////        this.errorCode = errorCode;
////    }
////
////    public long getTimestamp() {
////        return timestamp;
////    }
////
////    public void setTimestamp(long timestamp) {
////        this.timestamp = timestamp;
////    }
////
////    public String getPath() {
////        return path;
////    }
////
////    public void setPath(String path) {
////        this.path = path;
////    }
//}
