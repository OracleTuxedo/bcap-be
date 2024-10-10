package maas.bcap.craniumtest;

import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UInVo;
import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UInSub1Vo;
import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UOutVo;
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

public class DarrenTest {

    private static final Logger logger = LogManager.getLogger(DarrenTest.class);

    /// Request
    public static void interfaceTuxedoParseRequest() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Request START ###################################");
        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code("SAC04V127U");
        userData.setScrn_id("WAC0400400");
        userData.setClient_ip_no("172.16.20.11");
        userData.setOp_id("1787130271");
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");

        /// SAC04V127U Multi
        SAC04V127UInSub1Vo sac04v127uInSub1Vo1 = SAC04V127UInSub1Vo.builder()
            .row_no(1)
            .pmt_date("20230331")
            .acq_mb_no("008")
            .mid("71000204442")
            .auth_batch_no("308800350789")
            .pmt_seq_no(1)
            .reg_date("20230331")
            .reg_seq_no(1)
            .build();

        SAC04V127UInVo sac04v127uInVo = SAC04V127UInVo.builder()
            .sub1_vo(List.of(sac04v127uInSub1Vo1))
            .build();

        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn<SAC04V127UInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sac04v127uInVo);

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

        /// SAC04V127U
        String response = "00000987devaps01202410091731150024239400SAC04V127U              MTI R                        devaps0120241009173115002423940020241009173113380   UNIT      192.168.137.1                   8E96E6A577A5                           020241009173113380   20241009173115235592  0  00        000       NAZAP0002                                                        EN                                                                                                                                             N00000425                     30normal process success.                                                                                                                                                                                                                                                                                                                                                                                         00D00000050                     0000       1     140000W00065@@";

        /// Imitate Response from Tuxedo
        byte[] arrayOfByte = response
            .getBytes();

        SAC04V127UOutVo output = new SAC04V127UOutVo();

        TelegramUserDataOutput<SAC04V127UOutVo> telegramUserDataOutput = parse(arrayOfByte, output);

        output = telegramUserDataOutput.getOutput();

        logger.info(output.getClass().getSimpleName());
        logger.info(output.toString());

        logger.info("################################### Response END ###################################");

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
