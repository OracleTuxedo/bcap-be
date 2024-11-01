package maas.bcap.feature.screen.service;

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

import jakarta.servlet.http.HttpServletRequest;
import maas.bcap.feature.screen.dto.MessageTransferInDto;
import maas.bcap.feature.screen.dto.MessageTransferOutDto;
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

        /// Connect to Telegram Layer
        byte[] requestToTuxedo = originalMessage.getBytes();
        byte[] responseFromTuxedo = WeblogicConnector.connectTuxedo(requestToTuxedo);

        String responseMessage = new String(responseFromTuxedo, StandardCharsets.UTF_8);

        /// Generate new IV for another encryption
        String iv = generateRandomIv();

        /// Do Encryption response messages from Tuxedo
        String encryptedMessage = encrypt(responseMessage, iv);

        MessageTransferOutDto messageTransferOutDto = MessageTransferOutDto.builder()
                .encryptedMessages(encryptedMessage)
                .iv(iv)
                .build();

        return messageTransferOutDto;

    }

    private String encrypt(String message, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        // Decode Base64 untuk IV
        byte[] ivBytes = Base64.getDecoder().decode(iv);

        // Setup secret key dan IV
        SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Inisialisasi cipher untuk enkripsi
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        // Enkripsi data
        byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        // Encode hasil enkripsi ke Base64
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decrypt(String encryptedMessage, String iv)
            throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        /// Decode Base64 for IV and encrypted message
        byte[] ivBytes = Base64.getDecoder().decode(iv);
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(encryptedMessage);

        /// Setup secret key and IV
        SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        /// Initialize cipher for decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

        /// Perdorm decryption
        byte[] originalMessage = cipher.doFinal(encryptedMessageBytes);
        return new String(originalMessage, StandardCharsets.UTF_8);
    }

    private String generateRandomIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv);
    }
}
