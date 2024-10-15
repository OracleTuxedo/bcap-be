package mti.com.telegram.util;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.mapping.ByteDecoder;
import mti.com.telegram.mapping.ByteEncoder;
import mti.com.telegram.vo.*;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

/// Interface Telegram for Tuxedo Connection
public class InterfaceTelegramTest {

    private static final Logger logger = LogManager.getLogger(InterfaceTelegramTest.class);

    /// Request
    public static <V> String request(String tuxedoCode, V inVo) throws Exception {
        logger.info("################################### Request START ###################################");
        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code(tuxedoCode);
        userData.setScrn_id("WED030120H");
        userData.setClient_ip_no("172.16.20.11");
        userData.setOp_id("1787130271");
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");

        ByteEncoder byteEncoder = new ByteEncoder();

        TelegramIn<V> telegramIn = TelegramBuilder.getTelegramIn(userData, inVo);

        logger.info(telegramIn.getData().getData().toString());

        byte[] arrayOfByte = byteEncoder.convertObjectToBytes(telegramIn, true);
        String request = new String(arrayOfByte, StandardCharsets.UTF_8);

        logger.info(request);
        logger.info("################################### Request END ###################################");
        return request;
    }

    /// Response
    public static <T> TelegramUserDataOutput<T> response(String response, T outVo) throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Response START ###################################");

        byte[] arrayOfByte = response
            .getBytes();

        TelegramUserDataOutput<T> telegramUserDataOutput = parse(arrayOfByte, outVo);

        outVo = telegramUserDataOutput.getOutput();

        logger.info(outVo.getClass().getSimpleName());
        logger.info(outVo.toString());

        logger.info("################################### Response END ###################################");
        return telegramUserDataOutput;
    }

    private static <T> TelegramUserDataOutput<T> parse(byte[] arrayOfByte, T output) throws Exception {
        T object = null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte);
        logger.info(telegramHeader.toString());
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut<T> telegramOut1 = TelegramBuilder.getTelegramOutData(output);
            ByteDecoder<TelegramOut<T>> byteDecoder1 = new ByteDecoder<>();
            TelegramOut<T> telegramOut2 = byteDecoder1.convertBytes2Object(arrayOfByte, telegramOut1, true);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                throw new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramUserDataOutput<T> telegramUserDataOutput = new TelegramUserDataOutput<>();
            telegramUserDataOutput.setMessage(telegramMessage1);
            telegramUserDataOutput.setOutput(object);
            telegramUserDataOutput.setHeader(telegramHeader);

            return telegramUserDataOutput;
        }

        throw new TelegramNestedRuntimeException("No Data");
    }

    private static TelegramHeader getHeaderFromBytes(byte[] paramArrayOfbyte) throws Exception {
        TelegramHeader telegramHeader = new TelegramHeader();
        try {
            byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, 0, 500);
            ByteDecoder<TelegramHeader> byteDecoder = new ByteDecoder<>();
            telegramHeader = (TelegramHeader) byteDecoder.convertBytes2Object(arrayOfByte, telegramHeader, true);
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
            throw exception;
        }
        return telegramHeader;
    }
}
