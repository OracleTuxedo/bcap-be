package mti.com.telegram.util;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import mti.com.telegram.vo.TelegramData;
import mti.com.telegram.vo.TelegramDataList;
import mti.com.telegram.vo.TelegramDataOut;
import mti.com.telegram.vo.TelegramDataOutList;
import mti.com.telegram.vo.TelegramHeader;
import mti.com.telegram.vo.TelegramIn;
import mti.com.telegram.vo.TelegramInList;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramMessage;
import mti.com.telegram.vo.TelegramOut;
import mti.com.telegram.vo.TelegramOutList;
import mti.com.telegram.vo.TelegramOutNoData;
import mti.com.telegram.vo.TelegramTail;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TelegramBuilder {
    private static final Logger logger = LogManager.getLogger(TelegramBuilder.class);

    public TelegramBuilder() {
    }

    public TelegramIn getTelegramIn(TelegramInputUserData var1, Object var2) {
        TelegramHeader var3 = null;
        boolean var4 = false;
        TelegramIn var5 = new TelegramIn();

        try {
            var3 = this.makeTelegramHeader(var1);
            TelegramData var6 = this.makeTelegramData(var2);
            var5.setHeader(var3);
            var5.setData(var6);
            int var8 = TelegramUtil.getPacketSize(var5) - 8;
            var5.getHeader().setMsg_len(var8);
        } catch (Exception var7) {
            ExceptionUtil.logPrintStackTrace(logger, var7);
        }

        return var5;
    }

    public TelegramInList getTelegramInList(TelegramInputUserData var1, List<?> var2) {
        TelegramHeader var3 = null;
        boolean var4 = false;
        TelegramInList var5 = new TelegramInList();

        try {
            var3 = this.makeTelegramHeader(var1);
            TelegramDataList var6 = this.makeTelegramDataList(var2);
            var5.setHeader(var3);
            var5.setData(var6);
            int var8 = TelegramUtil.getPacketSize(var5) - 8;
            var5.getHeader().setMsg_len(var8);
        } catch (Exception var7) {
            ExceptionUtil.logPrintStackTrace(logger, var7);
        }

        return var5;
    }

    public TelegramHeader makeTelegramHeader(TelegramInputUserData var1) {
        TelegramHeader var2 = new TelegramHeader();
        String var3 = null;

        try {
            var3 = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException var6) {
            ExceptionUtil.logPrintStackTrace(logger, var6);
        }

        if (var3 == null) {
            var2.setGid_sysname("MAAS");
        } else {
            var2.setGid_sysname(var3);
        }

        var2.setGid_yyyyymmdd(TelegramDateUtil.getYYYYMMDD());
        var2.setGid_hhmmss(TelegramDateUtil.getHHMMSS());
        var2.setGid_seq(GlobalSeq.getSeq());
        String var4 = System.getProperty("wlinstance");
        var2.setGid_pid(TelegramUtil.addLeftZeroPaddingByLength(var4, 5));
        var2.setGid_stat("00");
        var2.setTx_code(var1.getTx_code());
        var2.setInst_no("MTI");
        var2.setSend_rspn_type("S");
        var2.setRspn_svc_code(var1.getRspn_svc_code());
        var2.setOri_global_id(this.getMakeOriginalGid(var2));
        var2.setOri_send_time(TelegramDateUtil.getMicroTime());
        String var5 = System.getProperty("chnl_id");
        if (var5 == null) {
            var2.setChnl_id("WEB");
        } else {
            var2.setChnl_id(var5);
        }

        var2.setClient_ip_no(var1.getClient_ip_no());
        var2.setClient_mac(var1.getClient_mac());
        var2.setScrn_id(var1.getScrn_id());
        var2.setScrn_lock_yn("N");
        var2.setOp_id(var1.getOp_id());
        var2.setXa_begin_flag(0);
        var2.setSend_time(TelegramDateUtil.getMicroTime());
        var2.setRspn_time("");
        var2.setSync_type(var1.getSync_type());
        if (var1.getSync_type() != null && "A".equals(var1.getSync_type())) {
            var2.setAsync_rspn_yn(var1.getAsync_rspn_yn());
        } else {
            var2.setAsync_rspn_yn("");
        }

        var2.setCall_depth(0);
        var2.setMsg_count_no(0);
        var2.setTtl_use_flag(var1.getTtl_use_flag());
        if (var1.getTtl() != 0) {
            var2.setTtl_from_time(TelegramDateUtil.getHHMMSS());
            var2.setTtl(var1.getTtl());
        } else {
            var2.setTtl_from_time("");
            var2.setTtl(0);
        }

        var2.setLong_msg_type(var1.getLong_msg_type());
        var2.setErr_flag(0);
        var2.setErr_src("");
        var2.setErr_type("");
        var2.setErr_code("");
        if (var1.getDst_inst_code() == null) {
            var2.setDst_inst_code("");
            var2.setFail_knd("");
            var2.setAp_host_name("");
            var2.setAp_caller_id("");
        } else {
            var2.setDst_inst_code(var1.getDst_inst_code());
            var2.setFail_knd(var1.getFail_knd());
            var2.setAp_host_name(var1.getAp_host_name());
            var2.setAp_caller_id(var1.getAp_caller_id());
        }

        var2.setInf_id("");
        var2.setLang_type(var1.getLang_type());
        var2.setReserved("");
        return var2;
    }

    public TelegramDataList makeTelegramDataList(List<?> var1) {
        TelegramDataList var2 = new TelegramDataList();
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

    public TelegramData makeTelegramData(Object var1) {
        TelegramData var2 = new TelegramData();
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

    public String getMakeOriginalGid(TelegramHeader var1) {
        String var2 = "";

        try {
            Field[] var3 = var1.getClass().getDeclaredFields();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
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

    public TelegramOut getTelegramOutData(Object var1) {
        TelegramOut var2 = new TelegramOut();
        TelegramHeader var3 = new TelegramHeader();
        TelegramMessage var4 = new TelegramMessage();
        TelegramDataOut var5 = this.makeTelegramOut(var1);
        TelegramTail var6 = new TelegramTail();
        var2.setHeader(var3);
        var2.setMessage(var4);
        var2.setData(var5);
        var2.setTail(var6);
        return var2;
    }

    public TelegramOut getTelegramOutData(TelegramHeader var1, TelegramMessage var2, Object var3) {
        TelegramOut var4 = new TelegramOut();
        TelegramDataOut var5 = this.makeTelegramOut(var3);
        TelegramTail var6 = new TelegramTail();
        var4.setHeader(var1);
        var4.setMessage(var2);
        var4.setData(var5);
        var4.setTail(var6);
        return var4;
    }

    public TelegramOutNoData getTelegramOutDataNoData() {
        TelegramOutNoData var1 = new TelegramOutNoData();
        TelegramHeader var2 = new TelegramHeader();
        TelegramMessage var3 = new TelegramMessage();
        TelegramTail var4 = new TelegramTail();
        var1.setHeader(var2);
        var1.setMessage(var3);
        var1.setTail(var4);
        return var1;
    }

    public TelegramOutList getTelegramOutDataList(List<Object> var1) {
        TelegramOutList var2 = new TelegramOutList();
        TelegramHeader var3 = new TelegramHeader();
        TelegramMessage var4 = new TelegramMessage();
        TelegramDataOutList var5 = this.makeTelegramOutList(var1);
        TelegramTail var6 = new TelegramTail();
        var2.setHeader(var3);
        var2.setMessage(var4);
        var2.setData(var5);
        var2.setTail(var6);
        return var2;
    }

    public TelegramDataOut makeTelegramOut(Object var1) {
        TelegramDataOut var2 = new TelegramDataOut();
        var2.setData(var1);
        return var2;
    }

    public TelegramDataOutList makeTelegramOutList(Object var1) {
        TelegramDataOutList var2 = new TelegramDataOutList();
        ArrayList var3 = new ArrayList();
        var2.setData(var3);
        return var2;
    }
}
