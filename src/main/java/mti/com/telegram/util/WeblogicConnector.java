package mti.com.telegram.util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Api to Weblogic Connector
 */
public class WeblogicConnector {

    /// TODO Add exception related to WebClient connection
    public static byte[] connectTuxedo(byte[] request) {

        String url = "http://localhost:7001/test1/connect";

        // Set headers to indicate plain text content
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

        // Create the HTTP entity with the plain text body and headers
        HttpEntity<byte[]> requestEntity = new HttpEntity<byte[]>(request, headers);

        // Send the request and receive a response
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);

        // Return the response body
        byte[] output = response.getBody();

        return (output != null) ? output : new byte[0];
    }

}
