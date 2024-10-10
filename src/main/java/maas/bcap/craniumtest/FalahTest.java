package maas.bcap.craniumtest;

import maas.bcap.module.ac.ac04.sac04v228r.SAC04V228RInVo;
import maas.bcap.module.ac.ac04.sac04v228r.SAC04V228ROutVo;
import maas.bcap.module.ac.ac06.sac06f244r.SAC06F244RInVo;
import maas.bcap.module.ac.ac06.sac06f244r.SAC06F244ROutVo;
import maas.bcap.module.ac.ac06.sac06f245r.SAC06F245RInVo;
import maas.bcap.module.ac.ac06.sac06f245r.SAC06F245ROutVo;
import maas.bcap.module.ac.ac06.sac06v610r.SAC06V610RInVo;
import maas.bcap.module.ac.ac06.sac06v610r.SAC06V610ROutVo;
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

public class FalahTest {

    private static final Logger logger = LogManager.getLogger(FalahTest.class);

    /// Request
    public static void interfaceTuxedoParseRequest() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Request START  ###################################");
        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code("SAC06V610R");
        userData.setScrn_id("WED030120H");
        userData.setClient_ip_no("172.16.20.11");
        userData.setOp_id("1787130271");
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");

//        /// SAC04V228R Single
//        SAC04V228RInVo sac04V228RInVo = SAC04V228RInVo.builder()
//            .snd_date("20210701")
//            .ems_seq_num(84075)
//            .build();

//  00000548                                SAC04V228R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010120959235                         00000      00000                                                                        EN                                                                                                                                              00000000                     20210701000000000000084075
//  00000550Falah   202410101207530020000000SAC04V228R              MTI S                        Falah   20241010120753002000000020241010120753000594WEB       172.16.20.11                                WED030120H N1787130271     020241010120753000597                    A000000      00010                                                                        EN                                                                                                                                             D00000047                     20210701000000000000084075@@

        /// SAC06F244R Single
//        SAC06F244RInVo sac06F244RInVo = SAC06F244RInVo.builder()
//            .prem_chk_no("0000000012")
//            .prem_chk_nm("coba")
//            .sql_id("TBACPREMCHKNRSLT_S0004")
//            .purc_sttl_biz_clcd("5")
//            .page_size(2)
//            .build();

//  00000935                                SAC06F244R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010115016306                         00000      00000                                                                        EN                                                                                                                                              00000000                     0000000012coba                                                                                                TBACPREMCHKNRSLT_S0004                  5                                                                                                                                                                                                                                                                000002
//  00000937Falah   202410101233040020000000SAC06F244R              MTI S                        Falah   20241010123304002000000020241010123304000230WEB       172.16.20.11                                WED030120H N1787130271     020241010123304000231                    A000000      00010                                                                        EN                                                                                                                                             D00000434                     0000000012coba                                                                                                TBACPREMCHKNRSLT_S0004                  5                                                                                                                                                                                                                                                                000002@@

        /// SAC06F245R Single
//        SAC06F245RInVo sac06F245RInVo = SAC06F245RInVo.builder()
//            .build();

//  00004618                                SAC06F245R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010132454763                         00000      00000                                                                        EN                                                                                                                                              00000000
//  00004620Falah   202410101326100020000000SAC06F245R              MTI S                        Falah   20241010132610002000000020241010132610000817WEB       172.16.20.11                                WED030120H N1787130271     020241010132610000820                    A000000      00010                                                                        EN                                                                                                                                             D00004117                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     @@

        /// SAC06V610R Single
        SAC06V610RInVo sac06V610RInVo = SAC06V610RInVo.builder()
            .mid("70000254969")
            .acq_mb_no("002")
            .acct_mgmt_bk_cd("0080017")
            .pmt_acct_no("1170005384953")
            .build();

//  00000563                                SAC06V610R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010133838576                         00000      00000                                                                        EN                                                                                                                                              00000000                     7000025496900200800171170005384953
//  00000565Falah   202410101340260020000000SAC06V610R              MTI S                        Falah   20241010134026002000000020241010134026000428WEB       172.16.20.11                                WED030120H N1787130271     020241010134026000430                    A000000      00010                                                                        EN                                                                                                                                             D00000062                     7000025496900200800171170005384953       @@


        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn<SAC06V610RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sac06V610RInVo);

        logger.info(telegramIn.getData().getData().toString());

        byte[] arrayOfByte = byteEncoder.convertObjectToBytes(telegramIn, true);
        String request = new String(arrayOfByte, StandardCharsets.UTF_8);

        logger.info(request);
        logger.info("################################### Request END  ###################################");
    }

    /// Response
    public static void interfaceTuxedoParseResponse() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Response START  ###################################");

        /// SAC04V228R
//        String response = "00001180devaps01202410101109470014257200SAC04V228R              MTI R                        devaps0120241010110947001425720020241010110947899   UNIT      192.168.1.16                    088FC37E596F                           020241010110947899   20241010110947385306  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000243                            155057     MSR_40001_MID_71000029413_402_20210701_20210701_1561.csv                                                                                                                                                1   @@";

        /// SAC06F244R
//        String response = "00001489devaps01202410101150150024257200SAC06F244R              MTI R                        devaps0120241010115015002425720020241010115016306   UNIT      192.168.1.16                    088FC37E596F                           020241010115016306   20241010115015853959  0  00        000       NMCAP0001                                                        EN                                                                                                                                             N00000525                     30Inquiry has been completed.                                                                                                                                                                                                                                                                                                                                                                                     01Inquiry has been completed.                                                                         D00000452                                                                                                                                                                                                                                                                                          2       1000000001220170113coba                                                                                                1674110016   5                     20170113@@";

        /// SAC06F245R
//        String response = "00000968devaps01202410101324540034257300SAC06F245R              MTI R                        devaps0120241010132454003425730020241010132454763   UNIT      192.168.1.16                    088FC37E596F                           020241010132454763   20241010132454296852  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000031                     0000000912@@";

        /// SAC06V610R
          String response = "00001163devaps01202410101338380044256900SAC06V610R              MTI R                        devaps0120241010133838004425690020241010133838576   UNIT      192.168.1.16                    088FC37E596F                           020241010133838576   20241010133838297945  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000625                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             02inquiry process success.                                                                            The Transaction Successfully Ended.                                                                 D00000026                     00009@@";

        /// Imitate Response from Tuxedo
        byte[] arrayOfByte = response
            .getBytes();

        SAC06V610ROutVo output = new SAC06V610ROutVo();

        TelegramUserDataOutput<SAC06V610ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);

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
