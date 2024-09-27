package mti.com.telegram.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.telegram.util.TelegramUtil;
import mti.com.telegram.vo.TelegramDataOut;
import mti.com.telegram.vo.TelegramDataOutList;

public class ByteDecoder {
    private String charSet = "UTF-8";
    private boolean limited = true;

    public ByteDecoder() {
    }

    public void setCharSet(String var1) {
        this.charSet = var1;
    }

    public Object convertBytes2Object(byte[] var1, Object var2, boolean var3) throws Exception {
        try {
            this.limited = var3;
            this.parseBytes(var1, var2);
            return var2;
        } catch (Exception var5) {
            throw var5;
        }
    }

    public Object convertBytes2Object(byte[] var1, Object var2, int var3) throws Exception {
        try {
            int var4 = var1.length - var3;
            byte[] var5 = TelegramUtil.cutBytes(var1, var3, var4);
            this.parseBytes(var5, var2);
            return var2;
        } catch (Exception var6) {
            throw var6;
        }
    }

    private void parseBytes(byte[] var1, Object var2) throws Exception {
        int var3 = 0;
        TelegramNestedRuntimeException var35;
        if (var1 == null) {
            var35 = new TelegramNestedRuntimeException("Byte input is null");
            var35.setParser("ByteDecoder");
            throw var35;
        } else if (var2 == null) {
            var35 = new TelegramNestedRuntimeException("Input Object is null");
            var35.setParser("ByteDecoder");
            throw var35;
        } else {
            Field[] var4 = var2.getClass().getDeclaredFields();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                FIELD var7 = (FIELD)var4[var6].getAnnotation(FIELD.class);
                int var8 = var7.length();
                String var9 = var4[var6].getName();

                try {
                    switch (var7.type()) {
                        case STRING:
                            byte[] var10 = TelegramUtil.cutBytes(var1, var3, var8);
                            String var36 = TelegramUtil.byte2StringTrimmed(var10, this.charSet);
                            String var12 = TelegramUtil.getSetterMethodName(var9);
                            Method var13 = var4[var6].getDeclaringClass().getDeclaredMethod(var12, String.class);
                            TelegramUtil.invokeMethod(var13, var2, var36);
                            var3 += var8;
                            break;
                        case NUMBER:
                            DATATYPE var14 = (DATATYPE)var4[var6].getAnnotation(DATATYPE.class);
                            var8 += var14.sign_length();
                            var8 += var14.point_length();
                            byte[] var15 = TelegramUtil.cutBytes(var1, var3, var8);
                            String var16 = TelegramUtil.getSetterMethodName(var9);
                            TelegramUtil.setterMethodForNumberInvoke(var2, var4[var6], var16, var15, this.charSet, "ByteDecoder");
                            var3 += var8;
                            break;
                        case LIST:
                            boolean var17 = false;
                            String var18 = TelegramUtil.getSetterMethodName(var9);
                            Method var19 = var4[var6].getDeclaringClass().getDeclaredMethod(var18, List.class);
                            int var37;
                            switch (var7.kind()) {
                                case DATA:
                                    byte[] var38 = TelegramUtil.cutBytes(var1, var3, 8);
                                    String var39 = (new String(var38)).trim();
                                    boolean var40 = Pattern.matches("^[0-9]*$", var39);
                                    if (!var40) {
                                        TelegramNestedRuntimeException var42 = new TelegramNestedRuntimeException("NumberFormat Exception");
                                        var42.setFieldName(var9);
                                        var42.setFtype(var7.type().getTypeName());
                                        var42.setObjName(var2.getClass().getName());
                                        var42.setMsg("Data Count String is [" + var39 + "]. is not NumberType");
                                        throw var42;
                                    }

                                    var37 = new Integer(var39);
                                    var3 += 8;
                                    ArrayList var41 = new ArrayList();

                                    for(int var44 = 0; var44 < var37; ++var44) {
                                        Type var47 = var4[var6].getGenericType();
                                        ParameterizedType var48 = (ParameterizedType)var47;
                                        Type[] var50 = var48.getActualTypeArguments();
                                        Class var52 = (Class)var50[0];
                                        Object var53 = var52.newInstance();
                                        int var54 = TelegramUtil.getPacketSize(var53);
                                        byte[] var55 = TelegramUtil.cutBytes(var1, var3, var54);
                                        this.parseBytes(var55, var53);
                                        var41.add(var53);
                                        var3 += var54;
                                        if (var1 != null && var3 > var1.length) {
                                            TelegramNestedRuntimeException var56 = new TelegramNestedRuntimeException("Data List length is abnormal");
                                            var56.setParser("ByteDecoder");
                                            throw var56;
                                        }
                                    }

                                    TelegramUtil.invokeMethod(var19, var2, var41);
                                    continue;
                                case MESSAGE:
                                    byte[] var43 = TelegramUtil.cutBytes(var1, var3, 2);
                                    var37 = new Integer(new String(var43));
                                    var3 += 2;
                                    ArrayList var46 = new ArrayList();

                                    for(int var45 = 0; var45 < var37; ++var45) {
                                        Type var49 = var4[var6].getGenericType();
                                        ParameterizedType var51 = (ParameterizedType)var49;
                                        Type[] var29 = var51.getActualTypeArguments();
                                        Class var30 = (Class)var29[0];
                                        Object var31 = var30.newInstance();
                                        int var32 = TelegramUtil.getPacketSize(var31);
                                        byte[] var33 = TelegramUtil.cutBytes(var1, var3, var32);
                                        this.parseBytes(var33, var31);
                                        var46.add(var31);
                                        var3 += var32;
                                    }

                                    TelegramUtil.invokeMethod(var19, var2, var46);
                                default:
                                    continue;
                            }
                        case VO:
                            Object var20 = var4[var6].get(var2);
                            if (var20 == null) {
                                var20 = TelegramUtil.getObjectFromField(var4[var6]);
                            }

                            byte[] var21 = TelegramUtil.cutBytes(var1, var3, var1.length - var3);
                            int var22 = TelegramUtil.getPacketSize(var20, var21, this.limited);
                            byte[] var23 = TelegramUtil.cutBytes(var1, var3, var22);
                            this.parseBytes(var23, var20);
                            String var24 = TelegramUtil.getSetterMethodName(var9);
                            Method var25 = null;
                            if (var2 instanceof TelegramDataOut) {
                                var25 = var4[var6].getDeclaringClass().getDeclaredMethod(var24, Object.class);
                            } else if (var2 instanceof TelegramDataOutList) {
                                var25 = var4[var6].getDeclaringClass().getDeclaredMethod(var24, List.class);
                            } else {
                                var25 = var4[var6].getDeclaringClass().getDeclaredMethod(var24, var20.getClass());
                            }

                            TelegramUtil.invokeMethod(var25, var2, var20);
                            var3 += var22;
                        case CHAR:
                        default:
                            break;
                        case BYTES:
                            byte[] var26 = TelegramUtil.cutBytes(var1, var3, var8);
                            String var27 = TelegramUtil.getSetterMethodName(var9);
                            Method var28 = var4[var6].getDeclaringClass().getDeclaredMethod(var27, byte[].class);
                            TelegramUtil.invokeMethod(var28, var2, var26);
                            var3 += var8;
                    }
                } catch (Exception var34) {
                    TelegramNestedRuntimeException var11 = new TelegramNestedRuntimeException(var34.toString());
                    var11.setFieldName(var9);
                    var11.setFtype(var7.type().getTypeName());
                    var11.setObjName(var2.getClass().getName());
                    var11.setPointer((long)var3);
                    var11.setMsg(var34.toString());
                    var11.setParser("ByteDecoder");
                    var11.setStackTrace(var34.getStackTrace());
                    throw var11;
                }
            }

        }
    }
}
