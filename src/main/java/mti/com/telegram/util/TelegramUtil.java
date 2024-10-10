package mti.com.telegram.util;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Pattern;

public class TelegramUtil {
    private static final Logger logger = LogManager.getLogger(TelegramUtil.class);

    public TelegramUtil() {
    }

    public static String getStringFromDecimalNumberRound(Object var0, Field var1) throws Exception {
        DATATYPE var2 = var1.getAnnotation(DATATYPE.class);
        int var3 = var2.decimal();
        NumberFormat var4 = NumberFormat.getInstance();
        var4.setGroupingUsed(false);
        var4.setMaximumFractionDigits(var3);
        var4.setRoundingMode(RoundingMode.HALF_UP);
        double var5 = 0.0;
        switch (var2.type()) {
            case DECIMAL:
                BigDecimal var7 = (BigDecimal) var0;
                var5 = var7.doubleValue();
                break;
            case DOUBLE:
                var5 = (Double) var0;
                break;
            case FLOAT:
                var5 = ((Float) var0).doubleValue();
                break;
            default:
                break;
        }

        return var4.format(var5);
    }

    public static String getStringFromDecimalNumberCeil(Object var0, Field var1) throws Exception {
        DATATYPE var2 = var1.getAnnotation(DATATYPE.class);
        int var3 = var2.decimal();
        NumberFormat var4 = NumberFormat.getInstance();
        var4.setGroupingUsed(false);
        var4.setMaximumFractionDigits(var3);
        var4.setRoundingMode(RoundingMode.CEILING);
        double var5 = 0.0;
        switch (var2.type()) {
            case DECIMAL:
                BigDecimal var7 = (BigDecimal) var0;
                var5 = var7.doubleValue();
                break;
            case DOUBLE:
                var5 = (Double) var0;
                break;
            case FLOAT:
                var5 = ((Float) var0).doubleValue();
                break;
            default:
                break;
        }

        return var4.format(var5);
    }

    public static String getStringFromDecimalNumberFloor(Object var0, Field var1) throws Exception {
        DATATYPE var2 = var1.getAnnotation(DATATYPE.class);
        int var3 = var2.decimal();
        NumberFormat var4 = NumberFormat.getInstance();
        var4.setGroupingUsed(false);
        var4.setMaximumFractionDigits(var3);
        var4.setRoundingMode(RoundingMode.FLOOR);
        double var5 = 0.0;
        switch (var2.type()) {
            case DECIMAL:
                BigDecimal var7 = (BigDecimal) var0;
                var5 = var7.doubleValue();
                break;
            case DOUBLE:
                var5 = (Double) var0;
                break;
            case FLOAT:
                var5 = ((Float) var0).doubleValue();
                break;
            default:
                break;
        }

        return var4.format(var5);
    }

    public static byte[] convertStringToBytes(Object var0, Field var1, String var2) throws Exception {
        byte[] var3 = null;
        FIELD var4 = var1.getAnnotation(FIELD.class);
        if (var0 == null) {
            return var3;
        } else {
            String var5 = var0 != null ? (String) var0 : "";
            int var6 = var4.length();
            int var7 = var5.getBytes(var2).length;
            if (var7 > var6) {
                byte[] var8 = var5.getBytes(var2);
                var3 = (new String(var8, 0, var6)).getBytes(var2);
            } else {
                TrimType var9 = var4.trim();
                switch (var4.type()) {
                    case STRING:
                        if (var9 == TrimType.LTRIM) {
                            var3 = lpadString2Byte(var5, var6, " ", var2);
                        } else {
                            var3 = rpadString2Byte(var5, var6, " ", var2);
                        }
                        break;
                    case NUMBER:
                        if (var9 == TrimType.LTRIM) {
                            var3 = lpadString2Byte(var5, var6, "0", var2);
                        } else {
                            var3 = rpadString2Byte(var5, var6, "0", var2);
                        }
                        break;
                    default:
                        break;
                }
            }

            return var3;
        }
    }

    public static String convertByteToHexString(byte[] var0) {
        if (var0 == null) {
            return null;
        } else {
            StringBuilder var1 = new StringBuilder();
            byte[] var2 = var0;
            int var3 = var0.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                byte var5 = var2[var4];
                var1.append(Integer.toString((var5 & 240) >> 4, 16));
                var1.append(Integer.toString(var5 & 15, 16));
            }

            return var1.toString();
        }
    }

    public static byte[] rpadString2Byte(String var0, int var1, String var2, String var3) throws Exception {
        byte[] var4 = null;
        StringBuffer var5 = new StringBuffer();
        int var9;
        if (var0 != null && !"".equals(var0)) {
            byte[] var12 = var0.getBytes(var3);
            int var11 = var12.length;
            int var6 = var1 - var11;
            if (var11 <= var1) {
                var5.append(var0);

                for (var9 = 0; var9 < var6; ++var9) {
                    var5.append(var2);
                }
            } else {
                byte[] var10 = cutBytes(var12, 0, var1);
                var5.append(new String(var10, var3));
            }
        } else {
            for (var9 = 0; var9 < var1; ++var9) {
                var5.append(var2);
            }
        }

        if (var5.toString() != null) {
            var4 = var5.toString().getBytes(var3);
        }

        return var4;
    }

    public static byte[] lpadString2Byte(String var0, int var1, String var2, String var3) throws Exception {
        byte[] var4 = null;
        StringBuffer var5 = new StringBuffer();
        int var9;
        if (var0 != null && !"".equals(var0)) {
            byte[] var12 = var0.getBytes(var3);
            int var11 = var12.length;
            int var6 = var1 - var11;
            if (var11 <= var1) {
                for (var9 = 0; var9 < var6; ++var9) {
                    var5.append(var2);
                }

                var5.append(var0);
            } else {
                byte[] var10 = cutBytes(var12, 0, var1);
                var5.append(new String(var10, var3));
            }
        } else {
            for (var9 = 0; var9 < var1; ++var9) {
                var5.append(var2);
            }
        }

        if (var5.toString() != null) {
            var4 = var5.toString().getBytes(var3);
        }

        return var4;
    }

    public static byte[] lpadString2ByteWithDecimal(String var0, int var1, String var2, String var3, int var4)
            throws Exception {
        byte[] var5 = null;
        int var7 = var0.indexOf(".");
        boolean var8 = false;
        int var13;
        if (var7 > 0) {
            String var9 = var0.substring(var7 + 1);
            var13 = var4 - var9.getBytes(var3).length;
        } else {
            var13 = var4;
            var8 = true;
        }

        int var14 = var1 - var0.getBytes(var3).length - var13;
        if (var8 && var4 > 0) {
            --var14;
        }

        char[] var10 = var0.toCharArray();
        StringBuffer var11;
        int var12;
        if (var10[0] == '-') {
            var11 = new StringBuffer();
            var11.append("-");

            for (var12 = 0; var12 < var14; ++var12) {
                var11.append(var2);
            }

            var11.append(var0.substring(1));

            for (var12 = 0; var12 < var13; ++var12) {
                if (var12 == 0 && var8) {
                    var11.append(".");
                }

                var11.append(var2);
            }

            if (var11.toString() != null) {
                var5 = var11.toString().getBytes(var3);
            }
        } else {
            var11 = new StringBuffer();

            for (var12 = 0; var12 < var14; ++var12) {
                var11.append(var2);
            }

            var11.append(var0);

            for (var12 = 0; var12 < var13; ++var12) {
                if (var12 == 0 && var8) {
                    var11.append(".");
                }

                var11.append(var2);
            }

            if (var11.toString() != null) {
                var5 = var11.toString().getBytes(var3);
            }
        }

        return var5;
    }

    public static String rightNumberPaddingStringWithDecimal(String var0, int var1, String var2, String var3, int var4)
            throws Exception {
        byte[] var5 = null;
        int var7 = var0.indexOf(".");
        boolean var8 = false;
        int var11;
        if (var7 > 0) {
            String var9 = var0.substring(var7 + 1);
            var11 = var4 - var9.getBytes(var3).length;
        } else {
            var11 = var4;
            var8 = true;
        }

        StringBuffer var12 = new StringBuffer();
        var12.append(var0);

        for (int var10 = 0; var10 < var11; ++var10) {
            if (var10 == 0 && var8) {
                var12.append(".");
            }

            var12.append(var2);
        }

        if (var12.toString() != null) {
            var5 = var12.toString().getBytes(var3);
        }

        return new String(var5);
    }

    public static int getPacketSize(Object var0) throws Exception {
        int packetSize = 0;
        Field[] fields = var0.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Ensure we can access private fields

            Object fieldValue = field.get(var0);
            FIELD fieldAnnotation = field.getAnnotation(FIELD.class);

            if (fieldAnnotation == null) {
                continue; // Skip if the annotation is not present
            }

            switch (fieldAnnotation.type()) {
                case NUMBER -> {
                    DATATYPE dataTypeAnnotation = field.getAnnotation(DATATYPE.class);
                    if (dataTypeAnnotation != null) {
                        packetSize += fieldAnnotation.length();
                        packetSize += dataTypeAnnotation.point_length();
                        packetSize += dataTypeAnnotation.sign_length();
                    }
                }
                case LIST -> {
                    if (fieldValue instanceof List<?> list) {
                        packetSize += getPacketSize(list);
                        switch (fieldAnnotation.kind()) {
                            case DATA -> packetSize += 8;
                            case MESSAGE -> packetSize += 2;
                            default -> {
                            }
                        }
                    }
                }
                case VO -> {
                    if (fieldValue == null) {
                        fieldValue = getObjectFromField(field);
                    }
                    if (fieldValue != null) {
                        packetSize += getPacketSize(fieldValue);
                    }
                }
                default -> packetSize += fieldAnnotation.length();
            }
        }

        return packetSize;
    }

    public static int getPacketSize(Object var0, byte[] var1, boolean var2) throws Exception {
        int var3 = 0;
        Field[] var4 = var0.getClass().getDeclaredFields();
        int var5 = var4.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            Field var7 = var4[var6];
            Object var8 = var7.get(var0);
            FIELD var9 = var7.getAnnotation(FIELD.class);
            byte[] var10 = null;
            switch (var9.type()) {
                case NUMBER:
                    DATATYPE var15 = var7.getAnnotation(DATATYPE.class);
                    var3 += var9.length();
                    var3 += var15.point_length();
                    var3 += var15.sign_length();
                    break;
                case LIST:
                    switch (var9.kind()) {
                        case DATA:
                            var10 = cutBytes(var1, var3, 8);
                            var3 += 8;
                            break;
                        case MESSAGE:
                            var10 = cutBytes(var1, var3, 2);
                            var3 += 2;
                            break;
                        default:
                            break;
                    }

                    String var11 = (new String(var10)).trim();
                    boolean var12 = Pattern.matches("^[0-9]*$", var11);
                    if (!var12) {
                        TelegramNestedRuntimeException var16 = new TelegramNestedRuntimeException(
                                "NumberFormat Exception");
                        var16.setMsg("Data Count String is [" + var11 + "]. is not NumberType");
                        throw var16;
                    }

                    int var13 = Integer.parseInt(var11);
                    if (var2 && (long) var13 > 10000L) {
                        TelegramNestedRuntimeException var17 = new TelegramNestedRuntimeException(
                                "NumberFormat Exception");
                        var17.setMsg("Data Count [" + var11 + "] is over Maximuim [" + 10000L + "]");
                        throw var17;
                    }

                    var3 += getListFieldSizeByCount(var7, var13);
                    break;
                case VO:
                    if (var8 == null) {
                        var8 = getObjectFromField(var7);
                    }

                    byte[] var14 = cutBytes(var1, var3, var1.length - var3);
                    var3 += getPacketSize(var8, var14, var2);
                    break;
                default:
                    var3 += var9.length();
            }
        }

        return var3;
    }

    public static int getPacketSize(List<Object> objectList) throws Exception {
        int packetSize = 0;
        if (objectList != null) {
            for (var obj : objectList) {
                packetSize += getPacketSize(obj);
            }
        }
        return packetSize;
    }

    public static int getPacketSize(Object[] var0) throws Exception {
        int var1 = 0;
        if (var0 != null) {
            Object[] var2 = var0;
            int var3 = var0.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object var5 = var2[var4];
                var1 += getPacketSize(var5);
            }
        }

        return var1;
    }

    public static int getListFieldSizeByCount(Field field, int count) throws Exception {
        Type genericType = field.getGenericType();
        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        Class<?> clazz = (Class<?>) typeArguments[0];
        Object instance = clazz.getDeclaredConstructor().newInstance();
        int packetSize = getPacketSize(instance);
        return packetSize * count;
    }

    public static byte[] cutBytes(byte[] var0, int var1, int var2) throws Exception {
        byte[] var3 = new byte[var2];
        int var4 = var1 + var2;
        int var5 = var1;

        for (int var6 = 0; var5 < var4; ++var6) {
            var3[var6] = var0[var5];
            ++var5;
        }

        return var3;
    }

    public static byte[] cutBytes1(byte[] var0, int var1, int var2) throws Exception {
        byte[] var3 = new byte[var2];
        int var4 = var1 + var2;
        System.out.println("소스[" + var1 + ":" + var4 + ":" + var2 + "] : [" + new String(var0) + "]");
        System.out.println("소스 길이 : " + var0.length);
        int var5 = var1;

        for (int var6 = 0; var5 < var4; ++var6) {
            var3[var6] = var0[var5];
            logger.info("bytes[{}] : {}", var5, (Byte.valueOf(var0[var5])).toString());
            ++var5;
        }

        for (var5 = 0; var5 < var0.length; ++var5) {
            System.out.print("bytes[" + var5 + "] : " + (Byte.valueOf(var0[var5])) + ",");
        }

        System.out.println();
        System.out.println("Result : " + new String(var3));
        return var3;
    }

    public static String byte2StringTrimmed(byte[] var0, String var1) throws Exception {
        return var0 == null ? null : (new String(var0, var1)).trim();
    }

    public static String getSetterMethodName(String var0) {
        String var1 = getAccessorName(var0);
        String var2 = "set" +
                var1;
        return var2;
    }

    public static String getAccessorName(String var0) {
        if (var0 != null && var0.length() > 0) {
            char[] var1 = var0.toCharArray();
            String var2 = Character.toUpperCase(var1[0]) +
                    var0.substring(1);
            return var2;
        } else {
            return "";
        }
    }

    public static void invokeMethod(Method var0, Object var1, Object var2) throws Exception {
        try {
            var0.invoke(var1, var2);
        } catch (Exception var5) {
            TelegramNestedRuntimeException var4 = new TelegramNestedRuntimeException(var5.toString());
            var4.setMethod(var0.getName());
            var4.setObjName(var1.getClass().getName());
            throw var4;
        }
    }

    public static Object getObjectFromField(Field field) throws Exception {
        Object instance = null;
        Type genericType = field.getGenericType();

        if (genericType instanceof ParameterizedType parameterizedType) {
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class<?> clazz = (Class<?>) actualTypeArguments[0];
            instance = clazz.getDeclaredConstructor().newInstance();
        } else {
            String className = genericType.getTypeName();
            instance = Class.forName(className).getDeclaredConstructor().newInstance();
        }

        return instance;
    }

    public static void setterMethodForNumberInvoke(Object var0, Field var1, String var2, byte[] var3, String var4,
            String var5) throws Exception {
        Method var6 = null;
        Type var7 = var1.getGenericType();
        String var8 = var7.getTypeName();
        DATATYPE var9 = var1.getAnnotation(DATATYPE.class);
        String var10 = byte2StringTrimmed(var3, var4);
        double var11 = Double.parseDouble(var10);
        if (isPrimitiveType(var7)) {
            if ("int".equals(var8)) {
                var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Integer.TYPE);
                if (verifyIntegerNumber((Double.valueOf(var11)).intValue(), var1, var5)) {
                    invokeMethod(var6, var0, (Double.valueOf(var11)).intValue());
                }
            }

            if ("short".equals(var8)) {
                var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Short.TYPE);
                if (verifyShortNumber((Double.valueOf(var11)).shortValue(), var1, var5)) {
                    invokeMethod(var6, var0, (Double.valueOf(var11)).shortValue());
                }
            }

            if ("long".equals(var8)) {
                var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Long.TYPE);
                if (verifyLongNumber((Double.valueOf(var11)).longValue(), var1, var5)) {
                    invokeMethod(var6, var0, (Double.valueOf(var11)).longValue());
                }
            }

            if ("float".equals(var8)) {
                var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Float.TYPE);
                if (verifyFloatNumber((Double.valueOf(var11)).floatValue(), var1, var5)) {
                    invokeMethod(var6, var0, (Double.valueOf(var11)).floatValue());
                }
            }

            if ("double".equals(var8)) {
                var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Double.TYPE);
                if (verifyDoubleNumber(var11, var1, var5)) {
                    invokeMethod(var6, var0, var11);
                }
            }
        } else {
            switch (var9.type()) {
                case DECIMAL:
                    var6 = var1.getDeclaringClass().getDeclaredMethod(var2, BigDecimal.class);
                    BigDecimal var13 = BigDecimal.valueOf(var11);
                    if (verifyDoubleNumber(var11, var1, var5)) {
                        invokeMethod(var6, var0, var13);
                    }
                    break;
                case DOUBLE:
                    var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Double.class);
                    if (verifyDoubleNumber(var11, var1, var5)) {
                        invokeMethod(var6, var0, var11);
                    }
                    break;
                case FLOAT:
                    var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Float.class);
                    if (verifyFloatNumber((float) var11, var1, var5)) {
                        invokeMethod(var6, var0, (float) var11);
                    }
                    break;
                case INT:
                    var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Integer.class);
                    if (verifyIntegerNumber((int) var11, var1, var5)) {
                        invokeMethod(var6, var0, (int) var11);
                    }
                    break;
                case LONG:
                    var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Long.class);
                    if (verifyLongNumber((long) (int) var11, var1, var5)) {
                        invokeMethod(var6, var0, (long) (int) var11);
                    }
                    break;
                case SHORT:
                    var6 = var1.getDeclaringClass().getDeclaredMethod(var2, Short.class);
                    if (verifyShortNumber((short) ((int) var11), var1, var5)) {
                        invokeMethod(var6, var0, (short) ((int) var11));
                    }
            }
        }

    }

    public static boolean isPrimitiveType(Type var0) {
        boolean var1 = false;
        String var2 = var0.getTypeName();
        if ("int".equals(var2) || "short".equals(var2) || "long".equals(var2) || "float".equals(var2)
                || "double".equals(var2)) {
            var1 = true;
        }

        return var1;
    }

    public static void viewObjectMethod(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        System.out.println("****************** " + object.getClass().getName());

        for (var field : fields) {
            Class<?> declaringClass = field.getDeclaringClass();
            Method[] methods = declaringClass.getMethods();

            for (var method : methods) {
                System.out.println(declaringClass.getName() + "                    " + method.getName());
                Type[] parameterTypes = method.getGenericParameterTypes();

                for (var parameterType : parameterTypes) {
                    System.out.println(
                            declaringClass.getName() + "                        " + parameterType.getTypeName());
                }
            }
        }

        System.out.println("*****************************************************");
    }

    public static String getHexaString(byte[] var0) {
        String var1 = null;

        try {
            StringBuffer var2 = new StringBuffer(var0.length * 2);

            for (int var4 = 0; var4 < var0.length; ++var4) {
                String var3 = "0" + Integer.toHexString(255 & var0[var4]);
                var2.append(var3.substring(var3.length() - 2));
            }

            var1 = var2.toString();
        } catch (Exception var5) {
            ExceptionUtil.logPrintStackTrace(logger, var5);
        }

        return var1;
    }

    public static String getMacAddress() {
        String var0 = null;

        try {
            InetAddress var1 = InetAddress.getLocalHost();
            NetworkInterface var2 = NetworkInterface.getByInetAddress(var1);
            if (var2 != null) {
                byte[] var3 = var2.getHardwareAddress();
                var0 = getHexaString(var3);
            }
        } catch (IOException var4) {
            ExceptionUtil.logPrintStackTrace(logger, var4);
        }

        return var0;
    }

    public static String getIpAddress() {
        String var0 = null;

        try {
            InetAddress var1 = InetAddress.getLocalHost();
            var0 = var1.getHostAddress();
        } catch (IOException var2) {
            ExceptionUtil.logPrintStackTrace(logger, var2);
        }

        return var0;
    }

    public static boolean verifyShortNumber(Object var0, Field var1, String var2) throws Exception {
        boolean var3 = true;
        FIELD var4 = var1.getAnnotation(FIELD.class);
        DATATYPE var5 = var1.getAnnotation(DATATYPE.class);
        int var6 = var4.length();
        int var7 = var5.sign_length();
        int var8 = var6 + var7;
        Type var9 = var1.getGenericType();
        if (isPrimitiveType(var9)) {
            String var10 = var9.getTypeName();
            if ("short".equals(var10)) {
                short var11 = (Short) var0;
                String var12 = (Short.valueOf(var11)).toString();
                int var13 = var12.length();
                String var14;
                TelegramNestedRuntimeException var15;
                if (var11 < 0) {
                    if (var13 > var8) {
                        var14 = "Short Value Verify is Failed";
                        var15 = new TelegramNestedRuntimeException(var14);
                        var15.setFieldName(var1.getName());
                        var15.setFtype(var4.type().getTypeName());
                        var15.setParser(var2);
                        var15.setMsg("Value : " + var12 + "[" + var8 + ":" + var13 + "]");
                        throw var15;
                    }
                } else if (var13 > var6) {
                    var14 = "Short Value Verify is Failed";
                    var15 = new TelegramNestedRuntimeException(var14);
                    var15.setFieldName(var1.getName());
                    var15.setFtype(var4.type().getTypeName());
                    var15.setParser(var2);
                    var15.setMsg("Value : " + var12 + "[" + var6 + ":" + var13 + "]");
                    throw var15;
                }
            } else {
                new Exception("Insert Object is not Short Type");
            }
        } else if (var1.getType().isAssignableFrom(Short.class)) {
            Short var16 = (Short) var0;
            String var17 = var16.toString();
            int var18 = var17.length();
            String var19;
            TelegramNestedRuntimeException var20;
            if (var16 < 0) {
                if (var18 > var8) {
                    var19 = "Short Value Verify is Failed";
                    var20 = new TelegramNestedRuntimeException(var19);
                    var20.setFieldName(var1.getName());
                    var20.setFtype(var4.type().getTypeName());
                    var20.setParser(var2);
                    var20.setMsg("Value : " + var17 + "[" + var8 + ":" + var18 + "]");
                    throw var20;
                }
            } else if (var18 > var6) {
                var19 = "Short Value Verify is Failed";
                var20 = new TelegramNestedRuntimeException(var19);
                var20.setFieldName(var1.getName());
                var20.setFtype(var4.type().getTypeName());
                var20.setParser(var2);
                var20.setMsg("Value : " + var17 + "[" + var6 + ":" + var18 + "]");
                throw var20;
            }
        } else {
            new Exception("Insert Object is not Short Type");
        }

        return var3;
    }

    public static boolean verifyIntegerNumber(Object var0, Field var1, String var2) throws Exception {
        boolean var3 = true;
        FIELD var4 = var1.getAnnotation(FIELD.class);
        DATATYPE var5 = var1.getAnnotation(DATATYPE.class);
        int var6 = var4.length();
        int var7 = var5.sign_length();
        int var8 = var6 + var7;
        Type var9 = var1.getGenericType();
        if (isPrimitiveType(var9)) {
            String var10 = var9.getTypeName();
            if ("int".equals(var10)) {
                int var11 = (Integer) var0;
                String var12 = (Integer.valueOf(var11)).toString();
                int var13 = var12.length();
                String var14;
                TelegramNestedRuntimeException var15;
                if (var11 < 0) {
                    if (var13 > var8) {
                        var14 = "Integer Value Verify is Failed";
                        var15 = new TelegramNestedRuntimeException(var14);
                        var15.setFieldName(var1.getName());
                        var15.setFtype(var4.type().getTypeName());
                        var15.setParser(var2);
                        var15.setMsg("Value : " + var12 + "[" + var8 + ":" + var13 + "]");
                        throw var15;
                    }
                } else if (var13 > var6) {
                    var14 = "Integer Value Verify is Failed";
                    var15 = new TelegramNestedRuntimeException(var14);
                    var15.setFieldName(var1.getName());
                    var15.setFtype(var4.type().getTypeName());
                    var15.setParser(var2);
                    var15.setMsg("Value : " + var12 + "[" + var6 + ":" + var13 + "]");
                    throw var15;
                }
            } else {
                new Exception("Insert Object is not Integer Type");
            }
        } else if (var1.getType().isAssignableFrom(Integer.class)) {
            Integer var16 = (Integer) var0;
            String var17 = var16.toString();
            int var18 = var17.length();
            String var19;
            TelegramNestedRuntimeException var20;
            if (var16 < 0) {
                if (var18 > var8) {
                    var19 = "Integer Value Verify is Failed";
                    var20 = new TelegramNestedRuntimeException(var19);
                    var20.setFieldName(var1.getName());
                    var20.setFtype(var4.type().getTypeName());
                    var20.setParser(var2);
                    var20.setMsg("Value : " + var17 + "[" + var8 + ":" + var18 + "]");
                    throw var20;
                }
            } else if (var18 > var6) {
                var19 = "Integer Value Verify is Failed";
                var20 = new TelegramNestedRuntimeException(var19);
                var20.setFieldName(var1.getName());
                var20.setFtype(var4.type().getTypeName());
                var20.setParser(var2);
                var20.setMsg("Value : " + var17 + "[" + var6 + ":" + var18 + "]");
                throw var20;
            }
        } else {
            new Exception("Insert Object is not Integer Type");
        }

        return var3;
    }

    public static boolean verifyLongNumber(Object var0, Field var1, String var2) throws Exception {
        boolean var3 = true;
        FIELD var4 = var1.getAnnotation(FIELD.class);
        DATATYPE var5 = var1.getAnnotation(DATATYPE.class);
        int var6 = var4.length();
        int var7 = var5.sign_length();
        int var8 = var6 + var7;
        Type var9 = var1.getGenericType();
        String var13;
        if (isPrimitiveType(var9)) {
            String var10 = var9.getTypeName();
            if ("long".equals(var10)) {
                long var11 = (Long) var0;
                var13 = (Long.valueOf(var11)).toString();
                int var14 = var13.length();
                String var15;
                TelegramNestedRuntimeException var16;
                if (var11 < 0L) {
                    if (var14 > var8) {
                        var15 = "Long Value Verify is Failed";
                        var16 = new TelegramNestedRuntimeException(var15);
                        var16.setFieldName(var1.getName());
                        var16.setFtype(var4.type().getTypeName());
                        var16.setParser(var2);
                        var16.setMsg("Value : " + var13 + "[" + var8 + ":" + var14 + "]");
                        throw var16;
                    }
                } else if (var14 > var6) {
                    var15 = "Long Value Verify is Failed";
                    var16 = new TelegramNestedRuntimeException(var15);
                    var16.setFieldName(var1.getName());
                    var16.setFtype(var4.type().getTypeName());
                    var16.setParser(var2);
                    var16.setMsg("Value : " + var13 + "[" + var6 + ":" + var14 + "]");
                    throw var16;
                }
            } else {
                new Exception("Insert Object is not Long Type");
            }
        } else if (var1.getType().isAssignableFrom(Long.class)) {
            Long var17 = (Long) var0;
            String var18 = var17.toString();
            int var12 = var18.length();
            TelegramNestedRuntimeException var19;
            if (var17 < 0L) {
                if (var12 > var8) {
                    var13 = "Long Value Verify is Failed";
                    var19 = new TelegramNestedRuntimeException(var13);
                    var19.setFieldName(var1.getName());
                    var19.setFtype(var4.type().getTypeName());
                    var19.setParser(var2);
                    var19.setMsg("Value : " + var18 + "[" + var8 + ":" + var12 + "]");
                    throw var19;
                }
            } else if (var12 > var6) {
                var13 = "Long Value Verify is Failed";
                var19 = new TelegramNestedRuntimeException(var13);
                var19.setFieldName(var1.getName());
                var19.setFtype(var4.type().getTypeName());
                var19.setParser(var2);
                var19.setMsg("Value : " + var18 + "[" + var6 + ":" + var12 + "]");
                throw var19;
            }
        } else {
            new Exception("Insert Object is not Long Type");
        }

        return var3;
    }

    public static boolean verifyFloatNumber(Object var0, Field var1, String var2) throws Exception {
        boolean var3 = true;
        FIELD var4 = var1.getAnnotation(FIELD.class);
        DATATYPE var5 = var1.getAnnotation(DATATYPE.class);
        int var6 = var4.length();
        int var7 = var5.decimal();
        int var8 = var5.sign_length();
        int var9 = var5.point_length();
        int var10 = var6 + var8 + var9;
        NumberFormat var11 = NumberFormat.getInstance();
        var11.setGroupingUsed(false);
        var11.setMaximumFractionDigits(var7);
        Type var12 = var1.getGenericType();
        String var15;
        String var22;
        TelegramNestedRuntimeException var24;
        if (isPrimitiveType(var12)) {
            String var13 = var12.getTypeName();
            if ("float".equals(var13)) {
                float var14 = (Float) var0;
                var15 = var11.format((Float.valueOf(var14)).doubleValue());
                var15 = rightNumberPaddingStringWithDecimal(var15, var10, "0", "UTF-8", var7);
                if (!verifyDecimalStringValue(var15, var7)) {
                    var22 = "Float Value Verify is Failed";
                    var24 = new TelegramNestedRuntimeException(var22);
                    var24.setFieldName(var1.getName());
                    var24.setFtype(var4.type().getTypeName());
                    var24.setParser(var2);
                    var24.setMsg("decimal is not correct : " + var15 + " decimal[" + var7 + "]");
                    throw var24;
                }

                int var16 = var15.length();
                String var17;
                TelegramNestedRuntimeException var18;
                if (var14 < 0.0F) {
                    if (var16 > var10) {
                        var17 = "Float Value Verify is Failed";
                        var18 = new TelegramNestedRuntimeException(var17);
                        var18.setFieldName(var1.getName());
                        var18.setFtype(var4.type().getTypeName());
                        var18.setParser(var2);
                        var18.setMsg("Value : " + var15 + "[" + var10 + ":" + var16 + "]");
                        throw var18;
                    }
                } else if (var16 > var6 + var9) {
                    var17 = "Float Value Verify is Failed";
                    var18 = new TelegramNestedRuntimeException(var17);
                    var18.setFieldName(var1.getName());
                    var18.setFtype(var4.type().getTypeName());
                    var18.setParser(var2);
                    var18.setMsg("Value : " + var15 + "[" + (var6 + var9) + ":" + var16 + "]");
                    throw var18;
                }
            } else {
                new Exception("Insert Object is not Float Type");
            }
        } else if (var1.getType().isAssignableFrom(Float.class)) {
            Float var19 = (Float) var0;
            String var20 = var11.format(var19.doubleValue());
            var20 = rightNumberPaddingStringWithDecimal(var20, var10, "0", "UTF-8", var7);
            if (!verifyDecimalStringValue(var20, var7)) {
                var15 = "Float Value Verify is Failed";
                TelegramNestedRuntimeException var23 = new TelegramNestedRuntimeException(var15);
                var23.setFieldName(var1.getName());
                var23.setFtype(var4.type().getTypeName());
                var23.setParser(var2);
                var23.setMsg("decimal is not correct : " + var20 + " decimal[" + var7 + "]");
                throw var23;
            }

            int var21 = var20.length();
            if (var19 < 0.0F) {
                if (var21 > var10) {
                    var22 = "Float Value Verify is Failed";
                    var24 = new TelegramNestedRuntimeException(var22);
                    var24.setFieldName(var1.getName());
                    var24.setFtype(var4.type().getTypeName());
                    var24.setParser(var2);
                    var24.setMsg("Value : " + var20 + "[" + var10 + ":" + var21 + "]");
                    throw var24;
                }
            } else if (var21 > var6 + var9) {
                var22 = "Float Value Verify is Failed";
                var24 = new TelegramNestedRuntimeException(var22);
                var24.setFieldName(var1.getName());
                var24.setFtype(var4.type().getTypeName());
                var24.setParser(var2);
                var24.setMsg("Value : " + var20 + "[" + (var6 + var9) + ":" + var21 + "]");
                throw var24;
            }
        } else {
            new Exception("Insert Object is not Float Type");
        }

        return var3;
    }

    public static boolean verifyDoubleNumber(Object var0, Field var1, String var2) throws Exception {
        boolean var3 = true;
        FIELD var4 = var1.getAnnotation(FIELD.class);
        DATATYPE var5 = var1.getAnnotation(DATATYPE.class);
        int var6 = var4.length();
        int var7 = var5.decimal();
        int var8 = var5.sign_length();
        int var9 = var5.point_length();
        NumberFormat var10 = NumberFormat.getInstance();
        var10.setGroupingUsed(false);
        var10.setMaximumFractionDigits(var7);
        int var11 = var6 + var8 + var9;
        Type var12 = var1.getGenericType();
        String var16;
        if (isPrimitiveType(var12)) {
            String var13 = var12.getTypeName();
            if ("double".equals(var13)) {
                double var14 = (Double) var0;
                var16 = var10.format(var14);
                var16 = rightNumberPaddingStringWithDecimal(var16, var11, "0", "UTF-8", var7);
                if (!verifyDecimalStringValue(var16, var7)) {
                    String var24 = "Double Value Verify is Failed";
                    TelegramNestedRuntimeException var26 = new TelegramNestedRuntimeException(var24);
                    var26.setFieldName(var1.getName());
                    var26.setFtype(var4.type().getTypeName());
                    var26.setParser(var2);
                    var26.setMsg("decimal is not correct : " + var16 + " decimal[" + var7 + "]");
                    throw var26;
                }

                int var17 = var16.length();
                String var18;
                TelegramNestedRuntimeException var19;
                if (var14 < 0.0) {
                    if (var17 > var11) {
                        var18 = "Double Value Verify is Failed";
                        var19 = new TelegramNestedRuntimeException(var18);
                        var19.setFieldName(var1.getName());
                        var19.setFtype(var4.type().getTypeName());
                        var19.setParser(var2);
                        var19.setMsg("Value : " + var16 + "[" + var11 + ":" + var17 + "]");
                        throw var19;
                    }
                } else if (var17 > var6 + var9) {
                    var18 = "Double Value Verify is Failed";
                    var19 = new TelegramNestedRuntimeException(var18);
                    var19.setFieldName(var1.getName());
                    var19.setFtype(var4.type().getTypeName());
                    var19.setParser(var2);
                    var19.setMsg("Value : " + var16 + "[" + (var6 + var9) + ":" + var17 + "]");
                    throw var19;
                }
            } else {
                new Exception("Insert Object is not Double Type");
            }
        } else if (var1.getType().isAssignableFrom(Double.class)) {
            Double var20 = (Double) var0;
            String var21 = var10.format(var20);
            var21 = rightNumberPaddingStringWithDecimal(var21, var11, "0", "UTF-8", var7);
            if (!verifyDecimalStringValue(var21, var7)) {
                String var22 = "Double Value Verify is Failed";
                TelegramNestedRuntimeException var23 = new TelegramNestedRuntimeException(var22);
                var23.setFieldName(var1.getName());
                var23.setFtype(var4.type().getTypeName());
                var23.setParser(var2);
                var23.setMsg("decimal is not correct : " + var21 + " decimal[" + var7 + "]");
                throw var23;
            }

            int var15 = var21.length();
            TelegramNestedRuntimeException var25;
            if (var20 < 0.0) {
                if (var15 > var11) {
                    var16 = "Double Value Verify is Failed";
                    var25 = new TelegramNestedRuntimeException(var16);
                    var25.setFieldName(var1.getName());
                    var25.setFtype(var4.type().getTypeName());
                    var25.setParser(var2);
                    var25.setMsg("Value : " + var21 + "[" + var11 + ":" + var15 + "]");
                    throw var25;
                }
            } else if (var15 > var6 + var9) {
                var16 = "Double Value Verify is Failed";
                var25 = new TelegramNestedRuntimeException(var16);
                var25.setFieldName(var1.getName());
                var25.setFtype(var4.type().getTypeName());
                var25.setParser(var2);
                var25.setMsg("Value : " + var21 + "[" + (var6 + var9) + ":" + var15 + "]");
                throw var25;
            }
        } else {
            new Exception("Insert Object is not Double Type");
        }

        return var3;
    }

    public static boolean verifyDecimalStringValue(String var0, int var1) {
        boolean var2 = false;
        if (!var0.contains(".")) {
            var2 = true;
        } else {
            String var3 = var0.substring(var0.indexOf(".") + 1);
            if (var1 >= var3.length()) {
                var2 = true;
            }
        }

        return var2;
    }

    public static String addLeftZeroPaddingByLength(String var0, int var1) {
        String var2 = "";
        if (var0 == null) {
            StringBuffer var3 = new StringBuffer();

            for (int var4 = 0; var4 < var1; ++var4) {
                var3.append("0");
            }

            var2 = var3.toString();
        } else if (var0.length() > var1) {
            var2 = var0.substring(var0.length() - var1);
        } else if (var0.length() == var1) {
            var2 = var0;
        } else {
            int var6 = var1 - var0.length();
            StringBuffer var7 = new StringBuffer();

            for (int var5 = 0; var5 < var6; ++var5) {
                var7.append("0");
            }

            var7.append(var0);
            var2 = var7.toString();
        }

        return var2;
    }

    public static String addRightZeroPaddingByLength(String var0, int var1) {
        String var2 = "";
        if (var0 == null) {
            StringBuffer var3 = new StringBuffer();

            for (int var4 = 0; var4 < var1; ++var4) {
                var3.append("0");
            }

            var2 = var3.toString();
        } else if (var0.length() > var1) {
            var2 = var0.substring(0, var1);
        } else if (var0.length() == var1) {
            var2 = var0;
        } else {
            int var6 = var1 - var0.length();
            StringBuffer var7 = new StringBuffer();
            var7.append(var0);

            for (int var5 = 0; var5 < var6; ++var5) {
                var7.append("0");
            }

            var2 = var7.toString();
        }

        return var2;
    }

    public static void main(String[] var0) {
        String var1 = "abcd";
        System.out.println(addRightZeroPaddingByLength(var1, 5));
    }
}
