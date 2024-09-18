package mti.com.telegram.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.mapping.ByteDecoder;
import mti.com.telegram.vo.TelegramHeader;
import mti.com.telegram.vo.TelegramMessage;
import mti.com.telegram.vo.TelegramOut;
import mti.com.telegram.vo.TelegramOutputUserData;
import mti.com.telegram.vo.TelegramTail;
import mti.com.utility.ExceptionUtil;
import mti.ed.ed03.vo.SED03F107ROutVo;

/**
 * 
 */

// SED03F107R
// 2024-09-18 13:09:14,521  INFO [mti.com.telegram.util.WtcConnector] Request[588] : [00000580DEVWAS0120240918130914054mti2100SED03F107R              MTI S                        DEVWAS0120240918130914054mti210020240918130914000520WEB       172.16.20.11                                WED030120H N1787130271     020240918130914000520                    A000000      00010                                                                        ID                                                                                                                                             D00000077                     EDC  0013000011976476                                   @@]
// 2024-09-18 13:09:15,706  INFO [mti.com.telegram.util.WtcConnector] Response[1172] : [00001164DEVWAS0120240918130914054mti2100SED03F107R              MTI R                        DEVWAS0120240918130914054mti210020240918130914000520WEB       172.16.20.11                                WED030120H N1787130271     02024091813091400052020240918130915691411A00  00        010                                                                        ID                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000227                              10013000011976476    Z900                                                   040101E0016     33   EDC Android PAX A920                                                                                @@]
// 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] TelegramOutputUserData [output=SED03F107ROutVo [count=1, sno=0013000011976476, whous_cd=Z900, rack_no=, icc_id=, sim_no=, srl_stat_cd=04, srl_st_cd=01, srl_loca_cd=01, vend_no=E0016, prd_cd=33, prd_nm=EDC Android PAX A920], outputList=null, message=TelegramMessage [kind=N, length=425, reserved=, msgAttr=3, msgAlm=0, message=, error_field_name=, error_info=, list=[]]]
// 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] ---------------------------- tx transaction Data ------------------------------------------
// 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] Data : 
// 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] SED03F107ROutVo [count=1, sno=0013000011976476, whous_cd=Z900, rack_no=, icc_id=, sim_no=, srl_stat_cd=04, srl_st_cd=01, srl_loca_cd=01, vend_no=E0016, prd_cd=33, prd_nm=EDC Android PAX A920]
// 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] Messge : 
// 2024-09-18 13:09:15,708 DEBUG [mti.ed.ed03.service.impl.ED0301SI] TelegramMessage [kind=N, length=425, reserved=, msgAttr=3, msgAlm=0, message=, error_field_name=, error_info=, list=[]]

public class InterfaceTelegram {
    private static final Logger logger = LogManager.getLogger(InterfaceTelegram.class);

    public static void interfaceTuxedo() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### START LeRucco ###################################");

        // Response from Tuxedo
        byte[] arrayOfByte = "00001164DEVWAS0120240918130914054mti2100SED03F107R              MTI R                        DEVWAS0120240918130914054mti210020240918130914000520WEB       172.16.20.11                                WED030120H N1787130271     02024091813091400052020240918130915691411A00  00        010                                                                        ID                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000227                              10013000011976476    Z900                                                   040101E0016     33   EDC Android PAX A920                                                                                @@"
                .getBytes();
        SED03F107ROutVo output = new SED03F107ROutVo();

        TelegramBuilder telegramBuilder = new TelegramBuilder();
        boolean bool = true;
        Object object = null;

        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut telegramOut1 = telegramBuilder.getTelegramOutData(output);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOut telegramOut2 = (TelegramOut) byteDecoder1.convertBytes2Object(arrayOfByte, telegramOut1, bool);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutput(object);
            telegramOutputUserData1.setHeader(telegramHeader);
            // return telegramOutputUserData1;
            logger.info("################################### END LeRucco ###################################");
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
