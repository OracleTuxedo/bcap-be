package mti.com.telegram.util;

import mti.com.telegram.mapping.ByteEncoder;
import mti.com.telegram.vo.*;
import mti.ed.ed03.vo.SED03F209RInVo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.mapping.ByteDecoder;
import mti.com.utility.ExceptionUtil;
import mti.ed.ed03.vo.SED03F107ROutVo;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * ## SED03F107R
 * 2024-09-18 13:09:14,521  INFO [mti.com.telegram.util.WtcConnector] Request[588] : [00000580DEVWAS0120240918130914054mti2100SED03F107R              MTI S                        DEVWAS0120240918130914054mti210020240918130914000520WEB       172.16.20.11                                WED030120H N1787130271     020240918130914000520                    A000000      00010                                                                        ID                                                                                                                                             D00000077                     EDC  0013000011976476                                   @@]
 * 2024-09-18 13:09:15,706  INFO [mti.com.telegram.util.WtcConnector] Response[1172] : [00001164DEVWAS0120240918130914054mti2100SED03F107R              MTI R                        DEVWAS0120240918130914054mti210020240918130914000520WEB       172.16.20.11                                WED030120H N1787130271     02024091813091400052020240918130915691411A00  00        010                                                                        ID                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000227                              10013000011976476    Z900                                                   040101E0016     33   EDC Android PAX A920                                                                                @@]
 * 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] TelegramOutputUserData [output=SED03F107ROutVo [count=1, sno=0013000011976476, whous_cd=Z900, rack_no=, icc_id=, sim_no=, srl_stat_cd=04, srl_st_cd=01, srl_loca_cd=01, vend_no=E0016, prd_cd=33, prd_nm=EDC Android PAX A920], outputList=null, message=TelegramMessage [kind=N, length=425, reserved=, msgAttr=3, msgAlm=0, message=, error_field_name=, error_info=, list=[]]]
 * 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] ---------------------------- tx transaction Data ------------------------------------------
 * 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] Data :
 * 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] SED03F107ROutVo [count=1, sno=0013000011976476, whous_cd=Z900, rack_no=, icc_id=, sim_no=, srl_stat_cd=04, srl_st_cd=01, srl_loca_cd=01, vend_no=E0016, prd_cd=33, prd_nm=EDC Android PAX A920]
 * 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] Messge :
 * 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] TelegramMessage [kind=N, length=425, reserved=, msgAttr=3, msgAlm=0, message=, error_field_name=, error_info=, list=[]]
 *
 * SED03F107ROutVo [count=1, sno=0013000011976476, whous_cd=Z900, rack_no=, icc_id=, sim_no=, srl_stat_cd=04, srl_st_cd=01, srl_loca_cd=01, vend_no=E0016, prd_cd=33, prd_nm=EDC Android PAX A920]
 * SED03F107ROutVo [count=1, sno=0013000011976476, whous_cd=Z900, rack_no=, icc_id=, sim_no=, srl_stat_cd=04, srl_st_cd=01, srl_loca_cd=01, vend_no=E0016, prd_cd=33, prd_nm=EDC Android PAX A920]
 *
 *
 * ## SED03F209R
 * 2024-09-18 13:13:08,803  INFO [mti.com.telegram.util.WtcConnector] Request[537] : [00000529DEVWAS0120240918131308065mti2100SED03F209R              MTI S                        DEVWAS0120240918131308065mti210020240918131308000802WEB       172.16.20.11                                WED030120H N1787130271     020240918131308000802                    A000000      00010                                                                        ID                                                                                                                                             D00000026                     Z900 @@]
 * 2024-09-18 13:13:08,838  INFO [mti.com.telegram.util.WtcConnector] Response[2284] : [00002276DEVWAS0120240918131308065mti2100SED03F209R              MTI R                        DEVWAS0120240918131308065mti210020240918131308000802WEB       172.16.20.11                                WED030120H N1787130271     02024091813130800080220240918131308823362A00  00        010       NAZAP0005                                                        ID                                                                                                                                             N00000525                     30Transaksi Berhasil Berakhir.                                                                                                                                                                                                                                                                                                                                                                                    01The Transaction Successfully Ended.                                                                 D00001239                           1110001     Building A, 2nd Floor, 201                                                                          10002     Building A, 2nd Floor, 201                                                                          10003     Building A, 2nd Floor, 201                                                                          10005     Building A, 2nd Floor, 201                                                                          10082     Dev by Sung 1                                                                                       10086     Dev by Sung 5                                                                                       10087     Dev by Sung 6                                                                                       10088     Test by Sung 6                                                                                      10089     Sung by Dev 6                                                                                       10090     RACK NO 5                                                                                           10091     SUNG TEST 10                                                                                        @@]
 * 2024-09-18 13:13:08,838 DEBUG [mti.ed.ed03.service.impl.ED0301SI] TelegramOutputUserData [output=mti.ed.ed03.vo.SED03F209ROutVo@138499f2, outputList=null, message=TelegramMessage [kind=N, length=525, reserved=, msgAttr=3, msgAlm=0, message=Transaksi Berhasil Berakhir., error_field_name=, error_info=, list=[TelegramSubMessage [subMsg=The Transaction Successfully Ended.]]]]
 * 2024-09-18 13:13:08,838 DEBUG [mti.ed.ed03.service.impl.ED0301SI] ---------------------------- tx transaction Data ------------------------------------------
 * 2024-09-18 13:13:08,838 DEBUG [mti.ed.ed03.service.impl.ED0301SI] Data :
 * 2024-09-18 13:13:08,838 DEBUG [mti.ed.ed03.service.impl.ED0301SI] mti.ed.ed03.vo.SED03F209ROutVo@138499f2
 * 2024-09-18 13:13:08,838 DEBUG [mti.ed.ed03.service.impl.ED0301SI] Messge :
 * 2024-09-18 13:13:08,838 DEBUG [mti.ed.ed03.service.impl.ED0301SI] TelegramMessage [kind=N, length=525, reserved=, msgAttr=3, msgAlm=0, message=Transaksi Berhasil Berakhir., error_field_name=, error_info=, list=[TelegramSubMessage [subMsg=The Transaction Successfully Ended.]]]
 *
 * 00000529LeRuccoL202409191307560020000000SED03F209R              MTI S                        LeRuccoL20240919130756002000000020240919130756000813WEB       172.16.20.11                                WED030120H N1787130271     020240919130756000814                    A000000      00010                                                                        EN                                                                                                                                             D00000026                     Z900 @@
 * 00000529DEVWAS0120240918131308065mti2100SED03F209R              MTI S                        DEVWAS0120240918131308065mti210020240918131308000802WEB       172.16.20.11                                WED030120H N1787130271     020240918131308000802                    A000000      00010                                                                        ID                                                                                                                                             D00000026                     Z900 @@
 *
 * ## Session
 * 2024-09-19 12:34:51,019 DEBUG [mti.az.az03.controller.AZ0301C] AZ0301 session - Id  :: GYgIxpn5nV4ut75PxwRDdupVD7U1eFaVOmTDke9lOxoDs-SM1AXd!1672226943!1726724086265
 * 2024-09-19 12:34:51,019 DEBUG [mti.az.az03.controller.AZ0301C] AZ0301 getUserData : SessionVO [sUserId=1787130271, sUserNm=Yosua Sutandar, sEmail=null, sUserTypeCd=null, orgnztId=null, uniqId=null, usrIno=, adm_usr_yn=Y, cdhdNo=null, hpgeId=null, cdhdNm=null, usrId=null, usrNm=null, usrClcd=null, mmpPrivCd=null, telNo=null, hpTelNo=null, lgrupId=null, lgrupNm=null, mgrupId=null, mgrupNm=null, mid=null, merNm=null, langClcd=null, usrCtgoCd=10, emailAddr=null, mmpsvcApplGrupCd=null]
 *
 */

public class InterfaceTelegram {
    private static final Logger logger = LogManager.getLogger(InterfaceTelegram.class);

    public static void interfaceTuxedoParseRequest() throws Exception {
        logger.info("################################### Request START LeRucco ###################################");
        TelegramInputUserData userData = new TelegramInputUserData();
        userData.setTx_code("SED03F209R");
        userData.setScrn_id("WED030120H");
        userData.setClient_ip_no("172.16.20.11");
        userData.setOp_id("1787130271");
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");

        SED03F209RInVo sed03F209RInVo = new SED03F209RInVo();
        sed03F209RInVo.setWhous_cd("Z900");

        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn telegramIn = telegramBuilder.getTelegramIn(userData, sed03F209RInVo);
        byte[] arrayOfByte = byteEncoder.convertObject2Bytes(telegramIn, true);
        String inputString = new String(arrayOfByte, StandardCharsets.UTF_8);

        logger.info(inputString);
        logger.info("################################### Request END LeRucco ###################################");
    }

    public static void interfaceTuxedoParseResponse() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Response START LeRucco ###################################");

        // SED03F107R
        String inputString = "00001164DEVWAS0120240918130914054mti2100SED03F107R              MTI R                        DEVWAS0120240918130914054mti210020240918130914000520WEB       172.16.20.11                                WED030120H N1787130271     02024091813091400052020240918130915691411A00  00        010                                                                        ID                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000227                              10013000011976476    Z900                                                   040101E0016     33   EDC Android PAX A920                                                                                @@";
        String checkString = "SED03F107ROutVo [count=1, sno=0013000011976476, whous_cd=Z900, rack_no=, icc_id=, sim_no=, srl_stat_cd=04, srl_st_cd=01, srl_loca_cd=01, vend_no=E0016, prd_cd=33, prd_nm=EDC Android PAX A920]";

        // Response from Tuxedo
        byte[] arrayOfByte = inputString
                .getBytes();

        SED03F107ROutVo output = new SED03F107ROutVo();

        TelegramOutputUserData telegramOutputUserData = parse(arrayOfByte, output);

        output = (SED03F107ROutVo) telegramOutputUserData.getOutput();

        logger.info(output.getClass().getSimpleName());
        String outputString = output.toString();
        logger.info(outputString);
        if (checkString.equals(outputString)){
            logger.info("Matched");
        } else {
            logger.info("Not Match");
        }

        logger.info("################################### Response END LeRucco ###################################");

    }

    public static TelegramOutputUserData parse(byte[] arrayOfByte, Object output) throws Exception {
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        Object object = null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut telegramOut1 = telegramBuilder.getTelegramOutData(output);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOut telegramOut2 = (TelegramOut) byteDecoder1.convertBytes2Object(arrayOfByte, telegramOut1, true);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                throw new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
            telegramOutputUserData.setMessage(telegramMessage1);
            telegramOutputUserData.setOutput(object);
            telegramOutputUserData.setHeader(telegramHeader);

            return telegramOutputUserData;
        }

        throw new TelegramNestedRuntimeException("No Data");
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
