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

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> decryptAndEncrypt(@RequestBody DecryptionRequest request) {
        try {
            // Dekripsi data menggunakan IV dari request
            String decryptedData = exampleService.decryptAES(request.getEncryptedData(), request.getIv());

            // Generate IV baru untuk enkripsi ulang
            String newIv = exampleService.generateRandomIv();

            // Enkripsi kembali hasil dekripsi dengan IV baru
            String reEncryptedData = exampleService.encryptAES(decryptedData, newIv);

            // Buat respons JSON
            Map<String, String> response = new HashMap<>();
            //response.put("decryptedData", decryptedData);

            response.put("encryptedData", reEncryptedData);
            response.put("iv", newIv);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Process failed: " + e.getMessage()));
        }

    

}



