package maas.bcap.craniumtest;

import maas.bcap.module.ed.ed03.sed03f075r.SED03F075RInVo;
import maas.bcap.module.ed.ed03.sed03f075r.SED03F075ROutVo;
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
 */

public class EndgTest {
    private static final Logger logger = LogManager.getLogger(EndgTest.class);

    /// Request
    public static void interfaceTuxedoParseRequest() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Request START LeRucco ###################################");
        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code("SED03F075R");//adjust
        userData.setScrn_id("WED030120H");//adjust -> opt
        userData.setClient_ip_no("172.16.20.11");//adjust -> opt
        userData.setOp_id("1787130271");//adjust -> opt
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");

        /// SED03F075R Single
        SED03F075RInVo sed03F075RInVo = SED03F075RInVo.builder()
            .page_size(40)
            .next_key("10")
            .inqr_cond_cd("01")
            .build();

        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn<SED03F075RInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, sed03F075RInVo);

        logger.info(telegramIn.getData().getData().toString());

        byte[] arrayOfByte = byteEncoder.convertObject2Bytes(telegramIn, true);
        String request = new String(arrayOfByte, StandardCharsets.UTF_8);

        logger.info(request);
        logger.info("################################### Request END Endg ###################################");
    }

    /// Response
    public static void interfaceTuxedoParseResponse() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Response START Endg ###################################");

        /// SED03F075R
        String response = "00003985devaps01202410091348490014239400SED03F075R              MTI R                        devaps0120241009134849001423940020241009134844374   UNIT      192.168.1.207                   0CDD2494CF5F                           020241009134844374   20241009134849624158  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000525                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             01The Transaction Successfully Ended.                                                                 D00002948                         40             32      2310   01IWL 225 G CTLS                                                                                      25   2024111600000012   02VX 520 D NON CTLS                                                                                   26   2024111600000013   02VX 520 G NON CTLS                                                                                   26   2024111600000014   02VX 520 G NON CTLS                                                                                   25   2024111600000015   02VX 520 G CTLS                                                                                       26   2024111600000016   02VX 675 3G CTLS                                                                                      25   2024111600000017   02Verifone VX 675 3G Mobile CTLS                                                                      25   2024111600000018   02VX 675 W CTLS                                                                                       25   2024111600000027   02Verifone Vx520C                                                                                     26   2018082915564628   01INGENICO MOVE 2500 G CTLS                                                                           25   2018053115014529   02PANDU - TEST                                                                                        08   2016121610230930   02VERIFONE C680                                                                                       25   2016121609210531   02Verifone C680 WIFI                                                                                  25   2019022614292932   01Ingenico MOVE2500 WIFI                                                                              25   2019022614313433   01EDC Android PAX A920                                                                                27   2020051413112334   01INGENICO ICT 229 GEM CL                                                                             25   2021080218311436   01Ingenico Desk 2600                                                                                  25   2022091313195837   08CASTLES VEGA3000                                                                                    25   2022102414374538   09NEWLAND SP630 PRO                                                                                   25   2022120617195540   01AXIUM DX 8000                                                                                       27   2023070717422141   09NewLand N910                                                                                        27   2023042808413242   09Test Product                                                                                        27   2024070304332599   10Sunmi P2                                                                                            27   20230404143135@@";

        /// Imitate Response from Tuxedo
        byte[] arrayOfByte = response
            .getBytes();

        SED03F075ROutVo output = new SED03F075ROutVo();

        TelegramUserDataOutput<SED03F075ROutVo> telegramUserDataOutput = parse(arrayOfByte, output);

        output = telegramUserDataOutput.getOutput();

        logger.info(output.getClass().getSimpleName());
        logger.info(output.toString());

        logger.info("################################### Response END LeRucco ###################################");

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
