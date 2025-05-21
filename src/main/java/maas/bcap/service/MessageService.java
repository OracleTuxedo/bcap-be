package maas.bcap.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import maas.bcap.dto.MessageTransferInDto;
import maas.bcap.dto.MessageTransferOutDto;
import mti.com.telegram.util.WeblogicConnector;

@Service
public class MessageService {
    private static final Logger log = LogManager.getLogger(MessageService.class);

    @Value("${aes.secret.key}")
    private String secretKey;

    public MessageTransferOutDto messageTransfer(HttpServletRequest request, MessageTransferInDto inDto)
            throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        /// Get original message after decryption
        String originalMessage = decrypt(inDto.getEncryptedMessage(), inDto.getIv());
        log.info("original message [{}]", originalMessage);

        /// Connect to Telegram Layer
        byte[] requestToTuxedo = originalMessage.getBytes();
        byte[] responseFromTuxedo = WeblogicConnector.connectTuxedo(requestToTuxedo);
        String responseMessage = new String(responseFromTuxedo, StandardCharsets.UTF_8);
        log.info("request to tuxedo [{}]", new String(requestToTuxedo, StandardCharsets.UTF_8));
        log.info("response from tuxedo [{}]", responseMessage);

        /// Generate new IV for another encryption
        String iv = generateRandomIv();

        /// Do Encryption response messages from Tuxedo
        String encryptedMessage = encrypt(responseMessage, iv);
        log.debug("encrypted message [" + encryptedMessage + "]");

        return MessageTransferOutDto.builder()
                .encryptedMessage(encryptedMessage)
                .iv(iv)
                .build();

    }

    private String encrypt(String message, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        // Decode Base64 untuk IV
        byte[] ivBytes = Base64.getDecoder().decode(iv);
        log.debug("ivBytes encrypt [{}]", new String(ivBytes, StandardCharsets.UTF_8));

        // Setup secret key dan IV
        SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Inisialisasi cipher untuk enkripsi
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        // Enkripsi data
        byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        log.debug("encrypted bytes [{}]", new String(encryptedBytes, StandardCharsets.UTF_8));

        // Encode hasil enkripsi ke Base64
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decrypt(String encryptedMessage, String iv)
            throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        /// Decode Base64 for IV and encrypted message
        byte[] ivBytes = Base64.getDecoder().decode(iv);
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(encryptedMessage);
        log.debug("ivBytes decrypt [{}]", new String(ivBytes, StandardCharsets.UTF_8));
        log.debug("encrypted messages bytes [{}]", new String(encryptedMessageBytes, StandardCharsets.UTF_8));

        /// Setup secret key and IV
        SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        /// Initialize cipher for decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

        /// Perform decryption
        byte[] originalMessage = cipher.doFinal(encryptedMessageBytes);
        log.debug("original message [{}]", new String(originalMessage, StandardCharsets.UTF_8));

        return new String(originalMessage, StandardCharsets.UTF_8);
    }

    private String generateRandomIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        log.debug("generateRandomIv [{}]", iv);
        return Base64.getEncoder().encodeToString(iv);
    }
}
