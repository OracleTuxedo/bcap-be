package mti.com.telegram.util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Api to Weblogic
 */
public class WeblogicConnector {

    public static byte[] connectTuxedo(byte[] request) {

        String url = "http://localhost:7001/test1/connect";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<byte[]> requestEntity = new HttpEntity<byte[]>(request, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);

        byte[] output = response.getBody();

        return (output != null) ? output : new byte[0];
    }
}
