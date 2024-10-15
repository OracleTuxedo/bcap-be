package maas.bcap.craniumtest;



//import maas.bcap.module.ed.ed03.sed03f169r.SED03F169RInVo;
//import maas.bcap.module.ed.ed03.sed03f169r.SED03F169ROutVo;
//import maas.bcap.module.ed.ed03.sed03f186r.SED03F186RInVo;
//import maas.bcap.module.ed.ed03.sed03f186r.SED03F186ROutVo;
//import maas.bcap.module.ed.ed03.sed03f187r.SED03F187RInVo;
//import maas.bcap.module.ed.ed03.sed03f187r.SED03F187ROutVo;
//import maas.bcap.module.ed.ed03.sed03f218r.SED03F218RInVo;
//import maas.bcap.module.ed.ed03.sed03f218r.SED03F218ROutVo;
//import maas.bcap.module.ed.ed03.sed03f219r.SED03F219RInVo;
//import maas.bcap.module.ed.ed03.sed03f219r.SED03F219ROutVo;
import maas.bcap.module.ed.ed03.sed03f220r.SED03F220RInVo;
import maas.bcap.module.ed.ed03.sed03f220r.SED03F220ROutVo;
import maas.bcap.module.ed.ed03.sed03f221r.SED03F221RInVo;
import maas.bcap.module.ed.ed03.sed03f221r.SED03F221ROutVo;
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


/************************************************************BATAS SED03F081R****************************************/

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
//        userData.setTx_code("SED03F120R");
//        userData.setScrn_id("WED030120H");
//        userData.setClient_ip_no("172.16.20.11");
//        userData.setOp_id("1787130271");
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        /// SED03F120R Single
//        SED03F120RInVo sed03f120rInVo = SED03F120RInVo.builder()
//            .wk_req_no("12000572280")
//            .mid("71001192425")
//            .build();
//
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SED03F120RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f120rInVo);
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
//        /// SED03F120R
//        String response = "00004067devaps01202410110957290014260100SED03F120R              MTI R                        devaps012024101109572900   1426010020241011095729329   UNIT      192.168.0.147                   04EA563255A5                           020241011095729329   20241011095729791701  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000525                     30-                                                                                                                                                                                                                                                                                                                                                                                                               01-                                                                                                   D00003030                     12000572280E0000     7100119242501   01                0000 03   12   2024041714383620240417143836              0000000000000000000                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        1200008666Z900                                                                                                                                                                 TEST BSI-4                                                                                          A01002A014510202AHMAD YANI                                                                      0217203241                                                                                11810 18794561                                                                                                                                                                                                                                                                                                       Edi Kurniawan                                                                                               BSI [451710011924252];                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  @@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SED03F120ROutVo output = new SED03F120ROutVo();
//
//        TelegramUserDataOutput<SED03F120ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);
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

/************************************************************BATAS SED03F120R****************************************/

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
//        userData.setTx_code("SED03F169R");
//        userData.setScrn_id("WED030120H");
//        userData.setClient_ip_no("172.16.20.11");
//        userData.setOp_id("1787130271");
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        /// SED03F120R Single
//        SED03F169RInVo sed03f120rInVo = SED03F169RInVo.builder()
//            .page_size(15)
//            .mid("71001196405")
//            .vend_no("E0000")
//            .build();
//
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SED03F169RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f120rInVo);
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
//        /// SED03F120R
//        String response = "00001178devaps01202410111401360024260400SED03F169R              MTI R                        devaps0120241011140136002426040020241011140136579   UNIT      192.168.0.147                   04EA563255A5                           020241011140136579   20241011140136976208  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000425                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             00D00000241                         15                    1       11200057234612   01   MY RSRV 20240627E0000A0210 20000059154                                                              71001196405E0000     20240711081947                             @@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SED03F169ROutVo output = new SED03F169ROutVo();
//
//        TelegramUserDataOutput<SED03F169ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);
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

/************************************************************BATAS SED03F169R****************************************/


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
//        userData.setTx_code("SED03F186R");
//        userData.setScrn_id("WED030120H");
//        userData.setClient_ip_no("172.16.20.11");
//        userData.setOp_id("1787130271");
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        /// SED03F186R Single
//        SED03F186RInVo sed03f186rInVo = SED03F186RInVo.builder()
//            .whous_cd("A020 ")
//            .acsr_prd_cd("03")
//            .rack_no("10051")
//            .build();
//
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SED03F186RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f186rInVo);
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
//        /// SED03F186R
//        String response = "00001007devaps01202410140951140014257200SED03F186R              MTI R                        devaps0120241014095114001425720020241014095114482   UNIT      192.168.0.147                   04EA563255A5                           020241014095114482   20241014095114433846  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000425                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             00D00000070                     A020 03          100000000000000015000010051     @@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SED03F186ROutVo output = new SED03F186ROutVo();
//
//        TelegramUserDataOutput<SED03F186ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);
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

/************************************************************BATAS SED03F186R****************************************/

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
//        userData.setTx_code("SED03F187R");
//        userData.setScrn_id("WED030120H");
//        userData.setClient_ip_no("172.16.20.11");
//        userData.setOp_id("1787130271");
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        /// SED03F186R Single
//        SED03F187RInVo sed03f187rInVo = SED03F187RInVo.builder()
//            .page_size(15)
//            .req_whous_cd("A020")
//            .apvl_stat_cd("RC")
//            .build();
//
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SED03F187RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f187rInVo);
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
//        /// SED03F186R
//        String response = "00001766devaps01202410141337220024257500SED03F187R              MTI R                        devaps0120241014133722002425750020241014133722797   UNIT      192.168.0.147                   04EA563255A5                           020241014133722797   20241014133722801270  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000425                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             00D00000829                         15                                                                                                                                                                                                                                                                        11      110000001382A020      01   18   04            1RCY0000001238A020      02   08   19            1RCY0000001213A020      01   22   04            1RCY0000001212A020      01   22   04            1RCY0000001209A020      01   22   04            1RCY0000001208A020      01   22   04            1RCY0000001207A020      01   22   04            1RCY0000001204A020      01   23   06            3RCY0000001202A020      01   22   04            1RCY0000001171A020      01   22   04            1RCY0000001168A020      01   22   04            2RCY@@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SED03F187ROutVo output = new SED03F187ROutVo();
//
//        TelegramUserDataOutput<SED03F187ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);
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

/************************************************************BATAS SED03F187R****************************************/

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
//        userData.setTx_code("SED03F218R");
//        userData.setScrn_id("WED030120H");
//        userData.setClient_ip_no("172.16.20.11");
//        userData.setOp_id("1787130271");
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        /// SED03F218R Single
//        SED03F218RInVo sed03f218rInVo = SED03F218RInVo.builder()
//            .page_size(20)
//            .agnc_cont_no("2016-E0001-02")
//            .odr_no("0000000261")
//            .build();
//
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SED03F218RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f218rInVo);
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
//        /// SED03F218R
//        String response = "00009856devaps01202410141653400054257100SED03F218R              MTI R                        devaps0120241014165340005425710020241014165340549   UNIT      192.168.0.147                   04EA563255A5                           020241014165340549   20241014165340506326  0  00        000       NAZAP0006                                                        EN                                                                                                                                             N00000525                     30The Next Data Exists.                                                                                                                                                                                                                                                                                                                                                                                           01The Next Data Exists.                                                                               D00008819                             2520161228134951                                                                                                                                                                                                                                                  20160000102                                                                                                                                                                                                                                                     0000000261                                                                                                                                                                                                                                                      05                                                                                                                                                                                                                                                              01                                                                                                                                                                                                                                                                    20201600001022016-E0001-02                 000000026105   01   20161214134429BEPS                                                                                                                                                                                                                                                                                                        201600001022016-E0001-02                 000000026105   01   20161214140746TYUTIUTUTUI                                                                                                                                                                                                                                                                                                 201600001022016-E0001-02                 000000026105   01   20161214141954rdtx 25                                                                                                                                                                                                                                                                                                     201600001022016-E0001-02                 000000026105   01   20161214155313BC                                                                                                                                                                                                                                                                                                          201600001022016-E0001-02                 000000026105   01   20161222170301This is also for test                                                                                                                                                                                                                                                                                       201600001022016-E0001-02                 000000026105   01   20161226144126Seoul                                                                                                                                                                                                                                                                                                       201600001022016-E0001-02                 000000026105   01   20161226145217BC Card Future Centre                                                                                                                                                                                                                                                                                       201600001022016-E0001-02                 000000026105   01   20161226172326Jakarta                                                                                                                                                                                                                                                                                                     201600001022016-E0001-02                 000000026105   01   20161227094039No One Sleep in Tokyo                                                                                                                                                                                                                                                                                       201600001022016-E0001-02                 000000026105   01   20161227100528You man                                                                                                                                                                                                                                                                                                     201600001022016-E0001-02                 000000026105   01   20161227104600BC                                                                                                                                                                                                                                                                                                          201600001022016-E0001-02                 000000026105   01   20161227105022bc                                                                                                                                                                                                                                                                                                          201600001022016-E0001-02                 000000026105   01   20161227105405kt                                                                                                                                                                                                                                                                                                          201600001022016-E0001-02                 000000026105   01   20161227105625lg                                                                                                                                                                                                                                                                                                          201600001022016-E0001-02                 000000026105   01   20161227105833DC                                                                                                                                                                                                                                                                                                          201600001022016-E0001-02                 000000026105   01   20161227110035Denpasar                                                                                                                                                                                                                                                                                                    201600001022016-E0001-02                 000000026105   01   20161227110814Tangerang                                                                                                                                                                                                                                                                                                   201600001022016-E0001-02                 000000026105   01   20161227111220Cikarang                                                                                                                                                                                                                                                                                                    201600001022016-E0001-02                 000000026105   01   20161227111440Tolong                                                                                                                                                                                                                                                                                                      201600001022016-E0001-02                 000000026105   01   20161228112220Hati Hati                                                                                                                                                                                                                                                                                                   @@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SED03F218ROutVo output = new SED03F218ROutVo();
//
//        TelegramUserDataOutput<SED03F218ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);
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

/************************************************************BATAS SED03F216R****************************************/

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
//        userData.setTx_code("SED03F219R");
//        userData.setScrn_id("WED030120H");
//        userData.setClient_ip_no("172.16.20.11");
//        userData.setOp_id("1787130271");
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        /// SED03F218R Single
//        SED03F219RInVo sed03f219rInVo = SED03F219RInVo.builder()
//            .page_size(15)
//            .next_key_val("1")
//            .odr_no("0000000261")
//            .cont_seq_no("20160000102")
//            .prd_cd("05")
//            .divs_odr_option_cd("01")
//            .exam_dttm("20170321113412")
//            .build();
//
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SED03F219RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f219rInVo);
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
//        /// SED03F219R
//        String response = "00001845devaps01202410151054500014254600SED03F219R              MTI R                        devaps0120241015105450001425460020241015105450096   UNIT      192.168.0.147                   04EA563255A5                           020241015105450096   20241015105450790551  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000908                     201600001022016-E0001-02                 000000026105   01   N20170321113412RDTX Tower                                                                                                                                                                                                                                                                                                  00000100Y                                                                                                                                                                                                                                                                                 1       1BAGUS                                                                                                                                                                                                                       @@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SED03F219ROutVo output = new SED03F219ROutVo();
//
//        TelegramUserDataOutput<SED03F219ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);
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

/************************************************************BATAS SED03F219R****************************************/

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
//        userData.setTx_code("SED03F220R");
//        userData.setScrn_id("WED030120H");
//        userData.setClient_ip_no("172.16.20.11");
//        userData.setOp_id("1787130271");
//        userData.setSync_type("A");
//        userData.setRspn_svc_code("");
//        userData.setAsync_rspn_yn("0");
//        userData.setTtl_use_flag(0);
//        userData.setLang_type("EN");
//
//        /// SED03F220R Single
//        SED03F220RInVo sed03f220rInVo = SED03F220RInVo.builder()
//            .prd_ctgo_cd("25")
//            .build();
//
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SED03F220RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f220rInVo);
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
//        /// SED03F219R
//        String response = "00003466devaps01202410151147230024254700SED03F220R              MTI R                        devaps0120241015114723002425470020241015114723324   UNIT      192.168.0.147                   04EA563255A5                           020241015114723324   20241015114724013209  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000525                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             01The Transaction Successfully Ended.                                                                 D00002429                           20Ingenico EFT 930 G                                                                                  01   25   01   0    Ingenico EFT 930                                                                                    02   25   01   1    ICT 250 3G CTLS                                                                                     05   25   01   1    IWL 220  3G MOVE TO IWL 220 G NON CTLS                                                              06   25   01   0    IWL 220 G NON CTLS                                                                                  07   25   01   1    IWL 220 G Mobile MOVE TO IWL 220 G NON CTLS                                                         08   25   01   1    IWL 220 W CTLS                                                                                      09   25   01   1    IWL 225 G CTLS                                                                                      10   25   01   1    VX 520 G NON CTLS                                                                                   14   25   01   1    VX 675 3G CTLS                                                                                      16   25   01   1    Verifone VX 675 3G Mobile CTLS                                                                      17   25   01   1    VX 675 W CTLS                                                                                       18   25   01   1    INGENICO MOVE 2500 G CTLS                                                                           28   25   01   1    VERIFONE C680                                                                                       30   25   01   1    Verifone C680 WIFI                                                                                  31   25   01   1    Ingenico MOVE2500 WIFI                                                                              32   25   01   1    INGENICO ICT 229 GEM CL                                                                             34   25   01   1    Ingenico Desk 2600                                                                                  36   25   01   1    CASTLES VEGA3000                                                                                    37   25   01   1    NEWLAND SP630 PRO                                                                                   38   25   01   1    @@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SED03F220ROutVo output = new SED03F220ROutVo();
//
//        TelegramUserDataOutput<SED03F220ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);
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

/************************************************************BATAS SED03F219R****************************************/

public class PanpanTest {

    private static final Logger logger = LogManager.getLogger(PanpanTest.class);

    /// Request
    public static void interfaceTuxedoParseRequest() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Request START Panpan ###################################");
        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code("SED03F221R");
        userData.setScrn_id("WED030120H");
        userData.setClient_ip_no("172.16.20.11");
        userData.setOp_id("1787130271");
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");

        /// SED03F221R Single
        SED03F221RInVo sed03f221rInVo = SED03F221RInVo.builder()
            .page_size(15)
            .next_key_val("1")
            .next_key_val1("1")
            .mid("71000464329")
            .build();

        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn<SED03F221RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03f221rInVo);

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

        /// SED03F221R
        String response = "00003564devaps01202410151418460044254800SED03F221R              MTI R                        devaps0120241015141846004425480020241015141846158   UNIT      192.168.0.147                   04EA563255A5                           020241015141846158   20241015141847231429  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00002627                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              2       273337111125224862810        37   06   ,QRIS International                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     73337111235324123           22   06   ,Off-us Debit                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           @@";

        /// Imitate Response from Tuxedo
        byte[] arrayOfByte = response
            .getBytes();

        SED03F221ROutVo output = new SED03F221ROutVo();

        TelegramUserDataOutput<SED03F221ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);

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
            TelegramOut<T> telegramOut2 = byteDecoder1.convertBytes2Object(arrayOfByte, telegramOut1, true);
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