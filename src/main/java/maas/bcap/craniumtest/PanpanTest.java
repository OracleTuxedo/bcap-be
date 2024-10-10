package maas.bcap.craniumtest;


import maas.bcap.module.ed.ed03.sed03f081r.SED03F081RInVo;
import maas.bcap.module.ed.ed03.sed03f081r.SED03F081ROutVo;
import maas.bcap.module.ed.ed03.sed03f120r.SED03F120RInVo;
import maas.bcap.module.ed.ed03.sed03f120r.SED03F120ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.mapping.ByteDecoder;
import mti.com.telegram.mapping.ByteEncoder;
import mti.com.telegram.util.TelegramBuilder;
import mti.com.telegram.util.TelegramUtil;
import mti.com.telegram.vo.*;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

/**
 * InVo -> Request String Panjang -> Weblogic -> Tuxedo -> Weblogic -> Response String panjang -> OutVo
 * Semangat 45 , tutor Github
 */

//public class PanpanTest {
//
//    private static final Logger logger = LogManager.getLogger(PanpanTest.class);
//
//    /// Request
//    public static void interfaceTuxedoParseRequest() throws Exception {
//        logger.atLevel(Level.ALL);
//
//        logger.info("################################### Request START Panpan ###################################");
//        TelegramUserDataInput userData = new TelegramUserDataInput();
//        userData.setTx_code("SED03F081R");
//        userData.setScrn_id("WED030120H");
//        userData.setClient_ip_no("172.16.20.11");
//        userData.setOp_id("1787130271");
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        /// SED03F081R Single
//        SED03F081RInVo sed03f081rInVo = SED03F081RInVo.builder()
//            .page_size(15)
//            .next_key_value("1")
//            .whous_cd("A020")
//            .cont_stat_cd("1")
//            .build();
//
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SED03F081RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f081rInVo);
//
//        logger.info(telegramIn.getData().getData().toString());
//
//        byte[] arrayOfByte = byteEncoder.convertObjectToBytes(telegramIn, true);
//        String request = new String(arrayOfByte, StandardCharsets.UTF_8);
//
//        logger.info(request);
//        logger.info("################################### Request END Panpan ###################################");
//    }
//
//    /// Response
//    public static void interfaceTuxedoParseResponse() throws Exception {
//        logger.atLevel(Level.ALL);
//
//        logger.info("################################### Response START Panpan ###################################");
//
//        /// SED03F081R
//        String response = "00001931devaps01202410091505150014239500SED03F081R              MTI R                        devaps0120241009150515001423950020241009150515318   UNIT      192.168.191.112                 04EA563255A5                           020241009150515318   20241009150515231527  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000425                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             00D00000994                         15                                                                                                                                                                                                                                                                         7       71234452394   testingwh                                                   082341233432        1Z100 1234543532   testing                                                     083452342321        1C010 1234567890   tes                                                         012314454464        1C010 2022100401   Product Dev Inq Only                                        2022100401          1Z100 2022100402   Product Dev Inq Only                                        2022100402          1Z100 2121212121   test                                                        21212121            1A020 9999999991   testingwh                                                   082341233432        1Z100 @@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SED03F081ROutVo output = new SED03F081ROutVo();
//
//        TelegramUserDataOutput<SED03F081ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);
//
//        output = telegramUserDataOutput.getOutput();
//
//        logger.info(output.getClass().getSimpleName());
//        logger.info(output.toString());
//
//        logger.info("################################### Response END Panpan ###################################");
//
//    }
//
//    public static <T> TelegramUserDataOutput<T> parse(byte[] arrayOfByte, T output) throws Exception {
//        T object = null;
//        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte);
////        logger.info(telegramHeader.toString());
//        if (telegramHeader.getErr_flag() == 0) {
//            TelegramOut<T> telegramOut1 = TelegramBuilder.<T>getTelegramOutData(output);
//            ByteDecoder<TelegramOut<T>> byteDecoder1 = new ByteDecoder<>();
//            TelegramOut<T> telegramOut2 = byteDecoder1.convertBytes2Object(arrayOfByte, telegramOut1, true);
//            TelegramTail telegramTail1 = telegramOut2.getTail();
//            if ("@@".equals(telegramTail1.getTail())) {
//                object = telegramOut2.getData().getData();
//            } else {
//                throw new TelegramNestedRuntimeException(
//                    "Response Telegram Length is not Matched !!");
//            }
//            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
//            TelegramUserDataOutput<T> telegramUserDataOutput = new TelegramUserDataOutput<T>();
//            telegramUserDataOutput.setMessage(telegramMessage1);
//            telegramUserDataOutput.setOutput(object);
//            telegramUserDataOutput.setHeader(telegramHeader);
//
//            return telegramUserDataOutput;
//        }
//
//        throw new TelegramNestedRuntimeException("No Data");
//    }
//
//    public static <T> TelegramHeader getHeaderFromBytes(byte[] paramArrayOfbyte) throws Exception {
//        TelegramHeader telegramHeader = new TelegramHeader();
//        try {
//            byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, 0, 500);
//            ByteDecoder<TelegramHeader> byteDecoder = new ByteDecoder<>();
//            telegramHeader = byteDecoder.convertBytes2Object(arrayOfByte, telegramHeader, true);
//        } catch (Exception exception) {
//            ExceptionUtil.logPrintStackTrace(logger, exception);
//            throw exception;
//        }
//        return telegramHeader;
//    }
//}

public class PanpanTest {

    private static final Logger logger = LogManager.getLogger(PanpanTest.class);

    /// Request
    public static void interfaceTuxedoParseRequest() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Request START Panpan ###################################");
        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code("SED03F120R");
        userData.setScrn_id("WED030120H");
        userData.setClient_ip_no("172.16.20.11");
        userData.setOp_id("1787130271");
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");

        /// SED03F120R Single
        SED03F120RInVo sed03f120rInVo = SED03F120RInVo.builder()
            .wk_req_no("12000572280")
            .mid("71001192425")
            .build();

        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn<SED03F120RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f120rInVo);

        logger.info(telegramIn.getData().getData().toString());

        byte[] arrayOfByte = byteEncoder.convertObjectToBytes(telegramIn, true);
        String request = new String(arrayOfByte, StandardCharsets.UTF_8);

        logger.info(request);
        logger.info("################################### Request END Panpan ###################################");
    }

    /// Response
    public static void interfaceTuxedoParseResponse() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Response START Panpan ###################################");

        /// SED03F081R
        String response = "00000544                                SED03F120R              MTI S                                                                            UNIT      192.168.63.112                  04EA563255A5                           020241010132844368                         00000      00000                                                                        EN                                                                                                                                              00000000                     1200057228071001192425";

        /// Imitate Response from Tuxedo
        byte[] arrayOfByte = response
            .getBytes();

        SED03F120ROutVo output = new SED03F120ROutVo();

        TelegramUserDataOutput<SED03F120ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);

        output = telegramUserDataOutput.getOutput();

        logger.info(output.getClass().getSimpleName());
        logger.info(output.toString());

        logger.info("################################### Response END Panpan ###################################");

    }

    public static <T> TelegramUserDataOutput<T> parse(byte[] arrayOfByte, T output) throws Exception {
        T object = null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte);
//        logger.info(telegramHeader.toString());
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut<T> telegramOut1 = TelegramBuilder.<T>getTelegramOutData(output);
            ByteDecoder<TelegramOut<T>> byteDecoder1 = new ByteDecoder<>();
            TelegramOut<T> telegramOut2 = byteDecoder1.(arrayOfByte, telegramOut1, true);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                throw new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramUserDataOutput<T> telegramUserDataOutput = new TelegramUserDataOutput<T>();
            telegramUserDataOutput.setMessage(telegramMessage1);
            telegramUserDataOutput.setOutput(object);
            telegramUserDataOutput.setHeader(telegramHeader);

            return telegramUserDataOutput;
        }

        throw new TelegramNestedRuntimeException("No Data");
    }

    public static <T> TelegramHeader getHeaderFromBytes(byte[] paramArrayOfbyte) throws Exception {
        TelegramHeader telegramHeader = new TelegramHeader();
        try {
            byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, 0, 500);
            ByteDecoder<TelegramHeader> byteDecoder = new ByteDecoder<>();
            telegramHeader = byteDecoder.convertBytes2Object(arrayOfByte, telegramHeader, true);
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
            throw exception;
        }
        return telegramHeader;
    }
}
