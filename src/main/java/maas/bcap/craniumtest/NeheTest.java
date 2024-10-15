package maas.bcap.craniumtest;

import maas.bcap.module.ac.ac01.SAC01F995RInVo;
import maas.bcap.module.ac.ac01.SAC01F995ROutVo;
import maas.bcap.module.ac.ac06.sac06v610r.SAC06V610ROutVo;
import maas.bcap.module.au.au01.sau01f710r.SAU01F710RInVo;
import maas.bcap.module.au.au01.sau01f710r.SAU01F710ROutVo;
import maas.bcap.module.au.au01.sau01f730r.SAU01F730R;
import maas.bcap.module.au.au01.sau01f730r.SAU01F730RInVo;
import maas.bcap.module.au.au01.sau01f730r.SAU01F730ROutVo;
import maas.bcap.module.mt.mt01.smt01f009c.SMT01F009CInSub1Vo;
import maas.bcap.module.mt.mt01.smt01f009c.SMT01F009CInVo;
import maas.bcap.module.mt.mt01.smt01f009c.SMT01F009COutVo;
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

import java.util.List;

/**
 * InVo -> Request String Panjang -> Weblogic -> Tuxedo -> Weblogic -> Response String panjang -> OutVo
 */
public class NeheTest {

    private static final Logger logger = LogManager.getLogger(NeheTest.class);

    /// Request
    public static void interfaceTuxedoParseRequest() throws Exception {
       logger.atLevel(Level.ALL);

       logger.info("################################### Request START ###################################");
        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code("SAU01F710R");
        userData.setScrn_id("WMT0100900");
        userData.setClient_ip_no("172.16.20.11");
        userData.setOp_id("1787130271");
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");


//        SMT01F009CInVo smt01F009CInVo = SMT01F009CInVo.builder()
//                .req_clcd("R")
//                .data_itm_no("")
//                .data_itm_nm("")
//                .next_key_val("")
//                .build();
        /// SMT01F009C Multi
//        SMT01F009CInSub1Vo smt01F009CInSub1Vo = SMT01F009CInSub1Vo.builder()
//            .req_clcd("R")
//            .data_itm_no("D00001")
//            .vald_strt_date("")
//            .vald_end_date("")
//            .data_itm_nm("")
//            .cret_mthd_clcd("")
//            .fnl_data_yn("")
//            .use_stop_yn("")
//            .parallel_proc_yn("")
//            .build();
//
//        SMT01F009CInVo smt01F009CInVo = SMT01F009CInVo.builder()
//            .req_clcd("R")
//            .data_itm_no("")
//            .data_itm_nm("")
//            .next_key_val("")
//            .sub1_vo(List.of(smt01F009CInSub1Vo))
//            .build();

//        SAU01F710RInVo sau01F710RInVo = SAU01F710RInVo.builder()
//                .next_page_exist_yn("N")
//                .next_key_val("008")
//                .mb_no("008")
//                .build();
        SAU01F730RInVo sau01F730R = SAU01F730RInVo.builder()
            .next_page_exist_yn("N")
            .next_key_val("123456789")
            .mb_no("008")
            .bin_no("123456789")
            .build();




//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SMT01F009CInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, smt01F009CInVo);
//        ByteEncoder byteEncoder = new ByteEncoder();
//        TelegramIn<SAC01F995RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sac01F995RInVo);

        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn<SAU01F730RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, new SAU01F730RInVo());

        logger.info(telegramIn.getData().getData().toString());

        byte[] arrayOfByte = byteEncoder.convertObjectToBytes(telegramIn, true);
        String request = new String(arrayOfByte, StandardCharsets.UTF_8);

        logger.info(request);
        logger.info("################################### Request END ###################################");
    }

    /// Response
    public static void interfaceTuxedoParseResponse() throws Exception {
      logger.atLevel(Level.ALL);

       logger.info("################################### Response START ###################################");

        /// SAC06V610R
//        String response = "00002505devaps01202410111317370024260200SAC01F995R              MTI R                        devaps0120241011131737002426020020241011131737087   UNIT      192.168.0.104                   505A65C1E155                           020241011131737087   20241011131737641086  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00001568                     436017042152120109315572017042149019170300728201704187100000037171000000371TEST MER-NAME LOAD 102                                                                              1711N/A                                                                                                 N4602210000190750   15    0360              26000                  0              26000                  0                  0                  011   N/A                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           0@@";

//        /// SAC01F995R
//        String response = "00002505devaps01202410141059220024257100SAC01F995R              MTI R                        devaps0120241014105922002425710020241014105922646   UNIT      192.168.1.11                    0CDD2494CF5F                           020241014105922646   20241014105922281803  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00001568                     436017042152120109315572017042149019170300728201704187100000037171000000371TEST MER-NAME LOAD 102                                                                              1711N/A                                                                                                 N4602210000190750   15    0360              26000                  0              26000                  0                  0                  011   N/A                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           0@@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SAC01F995ROutVo output = SAC01F995ROutVo.builder().build();
//
//        TelegramUserDataOutput<SAC01F995ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);

//        /// SAC01F995R
//        String response = "00001598devaps01202410151058250014254900SAU01F710R              MTI R                        devaps0120241015105825001425490020241015105827782   UNIT      192.168.0.104                   505A65C1E155                           020241015105827782   20241015105825913353  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000661                     N                                                                                                                                                                                                                                                                       1008Y11111111111Y  10   12                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0                   0    0@@";
//
//        /// Imitate Response from Tuxedo
//        byte[] arrayOfByte = response
//            .getBytes();
//
//        SAU01F710ROutVo output = SAU01F710ROutVo.builder().build();
//
//        TelegramUserDataOutput<SAU01F710ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);


        /// SAU01F730R
        String response = "00002078devaps01202410151333510034254900SAU01F730R              MTI R                        devaps0120241015133351003425490020241015133352109   UNIT      192.168.0.104                   505A65C1E155                           020241015133352109   20241015133351356371  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00001141                     N                                                                                                                                                                                                                                                                       1002123456789          00000000000000010002017040529991231                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                                      0000000000000000000                @@";

        /// Imitate Response from Tuxedo
        byte[] arrayOfByte = response
            .getBytes();

        SAU01F730ROutVo output = SAU01F730ROutVo.builder().build();

        TelegramUserDataOutput<SAU01F730ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);

        output = telegramUserDataOutput.getOutput();

        logger.info(output.getClass().getSimpleName());
        logger.info(output.toString());

        logger.info("################################### Response END  ###################################");
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


