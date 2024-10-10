package mti.com.telegram.util;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.mapping.ByteDecoder;
import mti.com.telegram.mapping.ByteEncoder;
import mti.com.telegram.vo.*;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

/// Interface Telegram for Tuxedo Connection
public class InterfaceTelegram {
    private static final Logger logger = LogManager.getLogger(InterfaceTelegram.class);

    public static <T, V> TelegramUserDataOutput<T> interfaceTuxedo(TelegramUserDataInput userDataInput, V inVo, T outVo) throws Exception {
        logger.info("#################### Interface Tuxedo ####################");
        logger.info(inVo.toString());
        logger.info(outVo.toString());
        boolean limited = true;
        ByteEncoder encoder = new ByteEncoder();
        TelegramIn<V> in = TelegramBuilder.getTelegramIn(userDataInput, inVo);
        byte[] requestToTuxedo = encoder.convertObjectToBytes(in, limited);

        logger.info(new String(requestToTuxedo, StandardCharsets.UTF_8));
        logger.info(in.toString());

        byte[] responseFromTuxedo = WeblogicConnector.connectTuxedo(requestToTuxedo);

        if (responseFromTuxedo.length == 0) return null;

//        logger.info(new String(responseFromTuxedo, StandardCharsets.UTF_8));
//        StringBuilder le = new StringBuilder();
//        for (byte b : responseFromTuxedo) {
//            le.append(b).append(", ");
//        }
//        logger.info(le.toString());

        TelegramHeader header = getHeaderFromBytes(responseFromTuxedo);
        TelegramTail tail;
        TelegramMessage message;
        TelegramUserDataOutput<T> outputUserData;

        logger.info(header.toString());
        logger.info(header.getErr_flag());
        // Success With Data
        if (header.getErr_flag() == 0) {
            ByteDecoder<TelegramOut<T>> decoder = new ByteDecoder<>();
            logger.info("With Data");
            TelegramOut<T> out1 = TelegramBuilder.getTelegramOutData(outVo);
            logger.info("out 1");
            TelegramOut<T> out2 = decoder.convertBytes2Object(responseFromTuxedo, out1, limited);
            logger.info("out 2");
            tail = out2.getTail();
            logger.info(tail.toString());
            logger.info(tail.getTail());
            if ("@@".equals(tail.getTail())) {
                T outVoTemp = out2.getData().getData();
                message = out2.getMessage();
                logger.info("###################### TelegramOutputUserData ######################");
                logger.info(header.toString());
                logger.info(message.toString());
                logger.info(outVoTemp.toString());
                outputUserData = new TelegramUserDataOutput<T>();
                outputUserData.setMessage(message);
                outputUserData.setOutput(outVoTemp);
                outputUserData.setHeader(header);
                return outputUserData;
            } else {
                throw new TelegramNestedRuntimeException("Response Telegram Length is not Matched !!");
            }
        } else {
            ByteDecoder<TelegramOutNoData> decoder = new ByteDecoder<>();
            logger.info("No Data");
            TelegramOutNoData outNoData1 = TelegramBuilder.getTelegramOutDataNoData();
            TelegramOutNoData outNoData2 = decoder.convertBytes2Object(responseFromTuxedo, outNoData1, limited);
            tail = outNoData2.getTail();
            if (!"@@".equals(tail.getTail())) {
                throw new TelegramNestedRuntimeException("Response Telegram Length is not Matched !!");
            } else {
                message = outNoData2.getMessage();
                outputUserData = new TelegramUserDataOutput<T>();
                outputUserData.setMessage(message);
                outputUserData.setOutput(null);
                outputUserData.setHeader(header);
                return outputUserData;
            }
        }

    }

    public static TelegramHeader getHeaderFromBytes(byte[] paramArrayOfbyte) throws Exception {
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
