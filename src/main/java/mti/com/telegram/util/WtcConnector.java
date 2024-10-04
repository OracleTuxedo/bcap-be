package mti.com.telegram.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class WtcConnector {

    public static byte[] connectTuxedo(byte[] request){

        String url = "http://localhost:7001/test1/connect";

        // Set headers to indicate plain text content
        HttpHeaders headers = new HttpHeaders();

//        headers.set("Content-Type", "application/octet-stream");
//        headers.set("Accept", "application/octet-stream");
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

//    public static byte[] connectTuxedo(byte[] request){
//
//        String url = "http://localhost:7001/test1/connect";
//
//        // Set headers to indicate plain text content
//        HttpHeaders headers = new HttpHeaders();
//
////        headers.set("Content-Type", "application/octet-stream");
////        headers.set("Accept", "application/octet-stream");
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));
//
//        // Create the HTTP entity with the plain text body and headers
//        HttpEntity<byte[]> requestEntity = new HttpEntity<byte[]>(new byte[0], headers);
//
//        // Send the request and receive a response
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
//
//        // Return the response body
//        byte[] output = response.getBody();
//
//        return (output != null) ? output : new byte[0];
//    }
}
