package mti.com.telegram.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;

public class WtcConnector {

    public static byte[] connectTuxedo(byte[] request){

        String url = "http://localhost:7002/test1/hi";

        // Set headers to indicate plain text content
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/plain");
        headers.set("Accept", "text/plain");

        String input = new String(request, StandardCharsets.UTF_8); // UTF-8 encoding

        // Create the HTTP entity with the plain text body and headers
        HttpEntity<String> requestEntity = new HttpEntity<String>(input, headers);

        // Send the request and receive a response
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Return the response body
        String output = response.getBody();

        return (output != null) ? output.getBytes(StandardCharsets.UTF_8) : new byte[0];
    }
}
