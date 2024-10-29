package maas.bcap.screen.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.screen.example.dto.ExampleInDto;
import maas.bcap.screen.example.dto.ExampleOutDto;
import maas.bcap.screen.example.dto.LoginInDto;
import maas.bcap.screen.example.dto.LoginOutDto;
import maas.bcap.screen.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


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

    @GetMapping("/login")
    public LoginOutDto loginGet(HttpServletRequest request) throws Exception {
        LoginInDto inDto = LoginInDto.builder()
            .build();
        return exampleService.login(request, inDto, "WAZ030102H");
    }

    @PostMapping("/login")
    public LoginOutDto login(HttpServletRequest request, @RequestBody LoginInDto inDto) throws Exception {

        return exampleService.login(request, inDto, "WAZ030102H");
    }

//    @GetMapping("/enc")
//    public String getMethodName() {
//        return exampleService.hello();
//        // return new String("Hi ho! You're using a GET Method for the request.");
//    }
//
//    @PostMapping("/dcenc")
//    public ResponseEntity<Map<String, String>> DecryptEncrypt(@RequestBody Map<String, String> requestData) {
//        String decryptedData;
//        String encryptedData;
//        String encodedData = requestData.get("data");
//        Map<String, String> responseData = new HashMap<>();
//
//
//        try {
//            decryptedData = exampleService.decrypt(encodedData);
//            responseData.put("decryptedData", decryptedData);
//        } catch (Exception e) {
//            responseData.put("error", "Decryption failed: " + e.getMessage());
//            return ResponseEntity.status(500).body(responseData);
//        }
//
//        try {
//            encryptedData = exampleService.encrypt(decryptedData);
//            responseData.put("encryptedData", encryptedData);
//        } catch (Exception e) {
//            responseData.put("error", "Encryption failed: " + e.getMessage());
//            return ResponseEntity.status(500).body(responseData);
//        }
//
//        return ResponseEntity.ok(responseData);
//    }
//
//    @PostMapping("/encdc")
//    public ResponseEntity<Map<String, String>> EncryptDecrypt(@RequestBody Map<String, String> requestData) {
//        String decryptedData;
//        String encryptedData;
//        String encodedData = requestData.get("data");
//        Map<String, String> responseData = new HashMap<>();
//
//        try {
//            encryptedData = exampleService.encrypt(encodedData);
//            responseData.put("encryptedData", encryptedData);
//        } catch (Exception e) {
//            responseData.put("error", "Encryption failed: " + e.getMessage());
//            return ResponseEntity.status(500).body(responseData);
//        }
//
//        try {
//            decryptedData = exampleService.decrypt(encryptedData);
//            responseData.put("decryptedData", decryptedData);
//        } catch (Exception e) {
//            responseData.put("error", "Decryption failed: " + e.getMessage());
//            return ResponseEntity.status(500).body(responseData);
//        }
//
//
//
//        return ResponseEntity.ok(responseData);
//    }
//
//    @GetMapping("/{encodedData}")
//    public ResponseEntity<String> getPostSimul(@PathVariable("encodedData") String encodedData) {
//        String first;
//        try {
//            first = exampleService.encrypt(encodedData);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Encryption failed: " + e.getMessage());
//        }
//
//        String decryptedData, encryptedData;
//        try {
//            decryptedData = exampleService.decrypt(first);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Decryption failed: " + e.getMessage());
//        }
//
//        try {
//            encryptedData = exampleService.encrypt(decryptedData);
//            return ResponseEntity.ok(encryptedData);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Encryption failed: " + e.getMessage());
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Map<String, String>> Decrypt(@RequestBody Map<String, String> requestData) {
//        String decryptedData;
//        String encryptedData;
//        String encodedData = requestData.get("data");
//        Map<String, String> responseData = new HashMap<>();
//
//        try {
//            encryptedData = exampleService.encrypt(encodedData);
//            responseData.put("encryptedData", encryptedData);
//        } catch (Exception e) {
//            responseData.put("error", "Encryption failed: " + e.getMessage());
//            return ResponseEntity.status(500).body(responseData);
//        }
//
//        try {
//            decryptedData = exampleService.decrypt(encryptedData);
//            responseData.put("decryptedData", decryptedData);
//        } catch (Exception e) {
//            responseData.put("error", "Decryption failed: " + e.getMessage());
//            return ResponseEntity.status(500).body(responseData);
//        }
//
//
//
//        return ResponseEntity.ok(responseData);
//    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> decryptData(@RequestBody DecryptionRequest request) {
        String decryptedData = exampleService.decryptAES(request.getEncryptedData(), request.getIv());
        return ResponseEntity.ok(decryptedData);
    }

    // Inner class for request model
    public static class DecryptionRequest {
        private String encryptedData;
        private String iv;

        // Getter and Setter for encryptedData
        public String getEncryptedData() {
            return encryptedData;
        }

        public void setEncryptedData(String encryptedData) {
            this.encryptedData = encryptedData;
        }

        // Getter and Setter for iv
        public String getIv() {
            return iv;
        }

        public void setIv(String iv) {
            this.iv = iv;
        }
    }

}



