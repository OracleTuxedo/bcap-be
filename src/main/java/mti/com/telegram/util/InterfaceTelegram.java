package mti.com.telegram.util;

import mti.com.telegram.mapping.ByteEncoder;
import mti.com.telegram.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.mapping.ByteDecoder;
import mti.com.utility.ExceptionUtil;

import java.nio.charset.StandardCharsets;

/// Interface Telegram for Tuxedo Connection
public class InterfaceTelegram {
    private static final Logger logger = LogManager.getLogger(InterfaceTelegram.class);

	public static TelegramUserDataOutput interfaceTuxedo(TelegramUserDataInput userDataInput, Object inVo, Object outVo) throws Exception {
        logger.info("#################### Interface Tuxedo ####################");
        logger.info(inVo.toString());
        logger.info(outVo.toString());
        boolean limited = true;
        ByteEncoder encoder = new ByteEncoder();
        TelegramIn in = TelegramBuilder.getTelegramIn(userDataInput, inVo);
        byte[] requestToTuxedo = encoder.convertObject2Bytes(in, limited);

        logger.info(new String(requestToTuxedo, StandardCharsets.UTF_8));
        logger.info(in.toString());

        byte[] responseFromTuxedo = WtcConnector.connectTuxedo(requestToTuxedo);

        if (responseFromTuxedo.length == 0) return null;

//        logger.info(new String(responseFromTuxedo, StandardCharsets.UTF_8));
//        StringBuilder le = new StringBuilder();
//        for (byte b : responseFromTuxedo) {
//            le.append(b).append(", ");
//        }
//        logger.info(le.toString());

		TelegramHeader header = getHeaderFromBytes(responseFromTuxedo);
		ByteDecoder decoder = new ByteDecoder();
		TelegramTail tail;
		TelegramMessage message;
		TelegramUserDataOutput outputUserData;

        logger.info(header.toString());
        logger.info(header.getErr_flag());
        // Success With Data
		if (header.getErr_flag() == 0) {
            logger.info("With Data");
			TelegramOut out1 = TelegramBuilder.getTelegramOutData(outVo);
            logger.info("out 1");
			TelegramOut out2 = (TelegramOut) decoder.convertBytes2Object(responseFromTuxedo, out1, limited);
            logger.info("out 2");
            tail = out2.getTail();
            logger.info(tail.toString());
            logger.info(tail.getTail());
			if ("@@".equals(tail.getTail())) {
                Object outVoTemp = out2.getData().getData();
                message = out2.getMessage();
                logger.info("###################### TelegramOutputUserData ######################");
                logger.info(header.toString());
                logger.info(message.toString());
                logger.info(outVoTemp.toString());
                outputUserData = new TelegramUserDataOutput();
                outputUserData.setMessage(message);
                outputUserData.setOutput(outVoTemp);
                outputUserData.setHeader(header);
				return outputUserData;
			} else {
                throw new TelegramNestedRuntimeException("Response Telegram Length is not Matched !!");
			}
		} else {
            logger.info("No Data");
			TelegramOutNoData outNoData1 = TelegramBuilder.getTelegramOutDataNoData();
			TelegramOutNoData outNoData2 = (TelegramOutNoData)decoder.convertBytes2Object(responseFromTuxedo, outNoData1, limited);
            tail = outNoData2.getTail();
			if (!"@@".equals(tail.getTail())) {
                throw new TelegramNestedRuntimeException("Response Telegram Length is not Matched !!");
			} else {
                message = outNoData2.getMessage();
                outputUserData = new TelegramUserDataOutput();
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
            ByteDecoder byteDecoder = new ByteDecoder();
            telegramHeader = (TelegramHeader) byteDecoder.convertBytes2Object(arrayOfByte, telegramHeader, true);
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
            throw exception;
        }
        return telegramHeader;
    }
}
