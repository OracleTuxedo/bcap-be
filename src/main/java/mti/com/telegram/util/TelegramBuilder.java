package mti.com.telegram.util;

import mti.com.telegram.vo.*;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class TelegramBuilder {
    private static final Logger logger = LogManager.getLogger(TelegramBuilder.class);

    public TelegramBuilder() {
    }

    public static <V> TelegramIn<V> getTelegramIn(TelegramUserDataInput userDataInput, V data) {
        TelegramHeader header = null;
        TelegramIn<V> telegramIn = new TelegramIn<>();

        try {
            header = makeTelegramHeader(userDataInput);
            TelegramInData<V> telegramData = makeTelegramData(data);
            telegramIn.setHeader(header);
            telegramIn.setData(telegramData);
            int msgLength = TelegramUtil.getPacketSize(telegramIn) - 8;
            telegramIn.getHeader().setMsg_len(msgLength);
        } catch (Exception e) {
            ExceptionUtil.logPrintStackTrace(logger, e);
        }

        return telegramIn;
    }

    public static TelegramInList getTelegramInList(TelegramUserDataInput userDataInput, List<?> dataList) {
        TelegramHeader header = null;
        TelegramInList telegramInList = new TelegramInList();

        try {
            header = makeTelegramHeader(userDataInput);
            TelegramInDataList telegramDataList = makeTelegramDataList(dataList);
            telegramInList.setHeader(header);
            telegramInList.setData(telegramDataList);
            int msgLength = TelegramUtil.getPacketSize(telegramInList) - 8;
            telegramInList.getHeader().setMsg_len(msgLength);
        } catch (Exception e) {
            ExceptionUtil.logPrintStackTrace(logger, e);
        }

        return telegramInList;
    }

    public static TelegramHeader makeTelegramHeader(TelegramUserDataInput userDataInput) {
        TelegramHeader header = new TelegramHeader();
        String hostName = null;

        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            ExceptionUtil.logPrintStackTrace(logger, e);
        }

        header.setGid_sysname(hostName != null ? hostName : "MAAS");
        header.setGid_yyyyymmdd(TelegramDateUtil.getYYYYMMDD());
        header.setGid_hhmmss(TelegramDateUtil.getHHMMSS());
        header.setGid_seq(GlobalSeq.getSeq());
        String wlInstance = System.getProperty("wlinstance");
        header.setGid_pid(TelegramUtil.addLeftZeroPaddingByLength(wlInstance, 5));
        header.setGid_stat("00");
        header.setTx_code(userDataInput.getTx_code());
        header.setInst_no("MTI");
        header.setSend_rspn_type("S");
        header.setRspn_svc_code(userDataInput.getRspn_svc_code());
        header.setOri_global_id(getMakeOriginalGid(header));
        header.setOri_send_time(TelegramDateUtil.getMicroTime());
        String chnlId = System.getProperty("chnl_id");
        header.setChnl_id(chnlId != null ? chnlId : "WEB");
        header.setClient_ip_no(userDataInput.getClient_ip_no());
        header.setClient_mac(userDataInput.getClient_mac());
        header.setScrn_id(userDataInput.getScrn_id());
        header.setScrn_lock_yn("N");
        header.setOp_id(userDataInput.getOp_id());
        header.setXa_begin_flag(0);
        header.setSend_time(TelegramDateUtil.getMicroTime());
        header.setRspn_time("");
        header.setSync_type(userDataInput.getSync_type());
        header.setAsync_rspn_yn("A".equals(userDataInput.getSync_type()) ? userDataInput.getAsync_rspn_yn() : "");
        header.setCall_depth(0);
        header.setMsg_count_no(0);
        header.setTtl_use_flag(userDataInput.getTtl_use_flag());
        header.setTtl_from_time(userDataInput.getTtl() != 0 ? TelegramDateUtil.getHHMMSS() : "");
        header.setTtl(userDataInput.getTtl());
        header.setLong_msg_type(userDataInput.getLong_msg_type());
        header.setErr_flag(0);
        header.setErr_src("");
        header.setErr_type("");
        header.setErr_code("");
        header.setDst_inst_code(userDataInput.getDst_inst_code() != null ? userDataInput.getDst_inst_code() : "");
        header.setFail_knd(userDataInput.getFail_knd());
        header.setAp_host_name(userDataInput.getAp_host_name());
        header.setAp_caller_id(userDataInput.getAp_caller_id());
        header.setInf_id("");
        header.setLang_type(userDataInput.getLang_type());
        header.setReserved("");

        return header;
    }

    public static TelegramInDataList makeTelegramDataList(List<?> var1) {
        TelegramInDataList var2 = new TelegramInDataList();
        var2.setDataType("D");
        var2.setData(var1);
        int var3 = 0;

        try {
            var3 = TelegramUtil.getPacketSize(var2) - 9;
        } catch (Exception var5) {
            ExceptionUtil.logPrintStackTrace(logger, var5);
        }

        var2.setLength(var3);
        return var2;
    }

    public static <V> TelegramInData<V> makeTelegramData(V data) {
        TelegramInData<V> telegramData = new TelegramInData<>();
        telegramData.setDataType("D");
        telegramData.setData(data);
        try {
            int dataLength = TelegramUtil.getPacketSize(telegramData) - 9;
            telegramData.setLength(dataLength);
        } catch (Exception e) {
            ExceptionUtil.logPrintStackTrace(logger, e);
        }
        return telegramData;
    }

    public static <T> TelegramOutDataList<T> makeTelegramOutList(List<T> dataList) {
        TelegramOutDataList<T> outDataList = new TelegramOutDataList<>();
        outDataList.setData(dataList); // Use the incoming list directly
        return outDataList;
    }

    public static String getMakeOriginalGid(TelegramHeader var1) {
        String var2 = "";

        try {
            Field[] var3 = var1.getClass().getDeclaredFields();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Field var6 = var3[var5];
                if (var6.getName().startsWith("gid_")) {
                    Object var7 = var6.get(var1);
                    var2 = var2 + new String(TelegramUtil.convertStringToBytes(var7, var6, "UTF-8"));
                }
            }
        } catch (Exception var8) {
            ExceptionUtil.logPrintStackTrace(logger, var8);
        }

        return var2;
    }

    public static <T> TelegramOut<T> getTelegramOutData(T var1) {
        TelegramOut<T> var2 = new TelegramOut<>();
        TelegramHeader var3 = new TelegramHeader();
        TelegramMessage var4 = new TelegramMessage();
        TelegramOutData<T> var5 = TelegramBuilder.makeTelegramOut(var1);
        TelegramTail var6 = new TelegramTail();
        var2.setHeader(var3);
        var2.setMessage(var4);
        var2.setData(var5);
        var2.setTail(var6);
        return var2;
    }

    public static <T> TelegramOut<T> getTelegramOutData(TelegramHeader var1, TelegramMessage var2, T var3) {
        TelegramOut<T> var4 = new TelegramOut<>();
        TelegramOutData<T> var5 = TelegramBuilder.makeTelegramOut(var3);
        TelegramTail var6 = new TelegramTail();
        var4.setHeader(var1);
        var4.setMessage(var2);
        var4.setData(var5);
        var4.setTail(var6);
        return var4;
    }

    public static TelegramOutNoData getTelegramOutDataNoData() {
        TelegramOutNoData var1 = new TelegramOutNoData();
        TelegramHeader var2 = new TelegramHeader();
        TelegramMessage var3 = new TelegramMessage();
        TelegramTail var4 = new TelegramTail();
        var1.setHeader(var2);
        var1.setMessage(var3);
        var1.setTail(var4);
        return var1;
    }

    public static <T> TelegramOutList<T> getTelegramOutDataList(List<T> var1) {
        TelegramOutList<T> var2 = new TelegramOutList<>();
        TelegramHeader var3 = new TelegramHeader();
        TelegramMessage var4 = new TelegramMessage();
        TelegramOutDataList<T> var5 = TelegramBuilder.<T>makeTelegramOutList(var1);
        TelegramTail var6 = new TelegramTail();
        var2.setHeader(var3);
        var2.setMessage(var4);
        var2.setData(var5);
        var2.setTail(var6);
        return var2;
    }

    public static <T> TelegramOutData<T> makeTelegramOut(T var1) {
        TelegramOutData<T> var2 = new TelegramOutData<>();
        var2.setData(var1);
        return var2;
    }
}
