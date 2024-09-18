//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mti.com.telegram.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TelegramDateUtil {
    public TelegramDateUtil() {
    }

    public static String getYYYYMMDD() {
        GregorianCalendar var0 = new GregorianCalendar();
        StringBuffer var1 = new StringBuffer(String.valueOf(((Calendar)var0).get(1)));
        var1.append(addLeftZeroFor2Bytes(String.valueOf(((Calendar)var0).get(2) + 1)));
        var1.append(addLeftZeroFor2Bytes(String.valueOf(((Calendar)var0).get(5))));
        return var1.toString();
    }

    public static String getHHMMSS() {
        GregorianCalendar var0 = new GregorianCalendar();
        StringBuffer var1 = new StringBuffer(addLeftZeroFor2Bytes(String.valueOf(((Calendar)var0).get(11))));
        var1.append(addLeftZeroFor2Bytes(String.valueOf(((Calendar)var0).get(12))));
        var1.append(addLeftZeroFor2Bytes(String.valueOf(((Calendar)var0).get(13))));
        return var1.toString();
    }

    public static String addLeftZeroFor2Bytes(String var0) {
        if (var0 == null) {
            return "00";
        } else {
            return var0.length() < 2 ? "0" + var0 : var0;
        }
    }

    public static String addLeftZeroFor3Bytes(String var0) {
        if (var0 == null) {
            return "000";
        } else if (var0.length() < 2) {
            return "00" + var0;
        } else {
            return var0.length() < 3 ? "0" + var0 : var0;
        }
    }

    public static String getMicroTime() {
        Date var0 = new Date();
        SimpleDateFormat var1 = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
        String var2 = var1.format(var0);
        return var2;
    }
}
