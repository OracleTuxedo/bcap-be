package mti.com.cipher;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncryption {

    // Metode untuk mengenkripsi dengan SHA-256
    public static String encrypt(String input) throws NoSuchAlgorithmException {
        // Inisialisasi MessageDigest dengan algoritma SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Mengubah string input menjadi byte array dan melakukan hash
        byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        // Mengonversi hasil hash ke dalam format hexadecimal
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        // Mengembalikan hasil hash dalam format uppercase (sesuai kebutuhan Anda)
        return hexString.toString().toUpperCase();
    }
}


//package mti.com.cipher;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//
//public class SHAEncryption {
//
//    public static String encrypt(String param) throws NoSuchAlgorithmException {
//        // Inisialisasi MessageDigest dengan algoritma SHA-256
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//
//        // Mengubah string input menjadi byte array dan melakukan hash
//        byte[] encodedHash = digest.digest(param.getBytes(StandardCharsets.UTF_8));
//
//        // Mengonversi hasil hash menjadi string berbasis Base64 atau hexadecimal
//        String encodedPassword = Base64.getEncoder().encodeToString(encodedHash);
//
//        return encodedPassword.toUpperCase();
//    }
//}

//package mti.com.cipher;
//
////import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class SHAEncryption {
//    public static String encrypt(String param) {
////        if (StringUtils.isEmpty(param)) {
//////            throw new EgovBizException("Input parameter cannot be empty");
////        }
//
//        PasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-256");
//        String encrypted = encoder.encode(param);
//        return encrypted.toUpperCase();
//    }
//}


//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class SHAEncryption {
//    // Use PBKDF2 with SHA-256 as a replacement for SHA-based encoding
////    private static final PasswordEncoder encoder = new Pbkdf2PasswordEncoder("", 185000, 256);
//
//    public static String encrypt(String param) {
//        if (param == null || param.isEmpty()) {
//            throw new IllegalArgumentException("Input parameter cannot be null or empty");
//        }
//
//        PasswordEncoder encoder = new Pbkdf2PasswordEncoder("", 64, 256, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//
//        // Use the encoder for hashing, similar to SHA-256 logic
//        String encodedPassword = encoder.encode(param);
//        return encodedPassword.toUpperCase(); // Maintain uppercasing for consistency
//    }
//}


//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

//public class SHAEncryption {
//    public static String encrypt(String param) throws EgovBizException{
//        if ( StringUtils.isEmpty(param)) {
//            /// TODO add exception
//        }
//        String rtnVal ;
//        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
//        rtnVal = encoder.encodePassword(param, null);
//        return rtnVal.toUpperCase();
//    }
//}