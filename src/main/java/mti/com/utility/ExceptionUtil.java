package mti.com.utility;

import org.apache.logging.log4j.Logger;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static void logPrintStackTrace(Logger var0, Exception var1) {
        if (var1 != null) {
            var0.error(var1.toString());
            StackTraceElement[] var2 = var1.getStackTrace();
            if (var2 != null) {
                StackTraceElement[] var3 = var2;
                int var4 = var2.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    StackTraceElement var6 = var3[var5];
                    var0.error(var6.toString());
                }
            }
        }

    }
}
