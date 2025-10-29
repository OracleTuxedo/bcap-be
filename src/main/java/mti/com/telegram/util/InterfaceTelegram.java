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
    private static final Logger log = LogManager.getLogger(InterfaceTelegram.class);

    public static <T, V> TelegramUserDataOutput<T> interfaceTuxedo(TelegramUserDataInput userDataInput, V inVo, T outVo)
            throws Exception {
        log.info("#################### Interface Tuxedo ####################");
        log.info(inVo.toString());
        log.info(outVo.toString());
        boolean limited = true;
        ByteEncoder encoder = new ByteEncoder();
        log.info("ByteEncoder");
        TelegramIn<V> in = TelegramBuilder.getTelegramIn(userDataInput, inVo);
        log.info(in);
        byte[] requestToTuxedo = encoder.convertObjectToBytes(in, limited);

        log.info(new String(requestToTuxedo, StandardCharsets.UTF_8));
        log.info(in.toString());

        // throw new Exception("STOP SAMPAI SINI");

        byte[] responseFromTuxedo = WeblogicConnector.connectTuxedo(requestToTuxedo);

        if (responseFromTuxedo.length == 0)
            return null;

        // log.info(new String(responseFromTuxedo, StandardCharsets.UTF_8));
        // StringBuilder le = new StringBuilder();
        // for (byte b : responseFromTuxedo) {
        // le.append(b).append(", ");
        // }
        // log.info(le.toString());

        TelegramHeader header = getHeaderFromBytes(responseFromTuxedo);
        TelegramTail tail;
        TelegramMessage message;
        TelegramUserDataOutput<T> outputUserData;

        log.info(header.toString());
        log.info(header.getErr_flag());
        // Success With Data
        if (header.getErr_flag() == 0) {
            ByteDecoder<TelegramOut<T>> decoder = new ByteDecoder<>();
            log.info("With Data");
            TelegramOut<T> out1 = TelegramBuilder.getTelegramOutData(outVo);
            log.info("out 1");
            TelegramOut<T> out2 = decoder.convertBytes2Object(responseFromTuxedo, out1, limited);
            log.info("out 2");
            tail = out2.getTail();
            log.info(tail.toString());
            log.info(tail.getTail());
            if ("@@".equals(tail.getTail())) {
                T outVoTemp = out2.getData().getData();
                message = out2.getMessage();
                log.info("###################### TelegramOutputUserData ######################");
                log.info(header.toString());
                log.info(message.toString());
                log.info(outVoTemp.toString());
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
            log.info("No Data");
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
            ExceptionUtil.logPrintStackTrace(log, exception);
            throw exception;
        }
        return telegramHeader;
    }
}
