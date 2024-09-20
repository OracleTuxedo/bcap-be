package mti.com.telegram.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.telegram.util.TelegramUtil;

public class ByteEncoder {
    private String charSet = "UTF-8";
    private boolean limited = true;

    public ByteEncoder() {
    }

    public void setCharSet(String var1) {
        this.charSet = var1;
    }

    public byte[] convertObject2Bytes(Object var1, boolean var2) throws Exception {
        this.limited = var2;
        ByteBuffer var3 = null;
        if (var1 == null) {
            return null;
        } else {
            int var4 = TelegramUtil.getPacketSize(var1);
            var3 = ByteBuffer.allocate(var4);
            Field[] var5 = var1.getClass().getDeclaredFields();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Field var8 = var5[var7];
                FIELD var9 = var8.getAnnotation(FIELD.class);
                Object var10 = var8.get(var1);

                try {
                    if (var10 == null) {
                        byte[] var17;
                        boolean var11 = false;
                        var17 = null;
                        int var14;
                        int var16;
                        label74:
                        switch (var9.type()) {
                            case STRING:
                                var16 = var9.length();
                                var17 = new byte[var16];
                                int var19 = 0;

                                while(true) {
                                    if (var19 >= var16) {
                                        break label74;
                                    }

                                    var17[var19] = 32;
                                    ++var19;
                                }
                            case NUMBER:
                                var16 = var9.length();
                                DATATYPE var13 = var8.getAnnotation(DATATYPE.class);
                                var16 += var13.sign_length();
                                var16 += var13.point_length();
                                var17 = new byte[var16];
                                var14 = 0;

                                while(true) {
                                    if (var14 >= var16) {
                                        break label74;
                                    }

                                    var17[var14] = 48;
                                    ++var14;
                                }
                            case LIST:
                                switch (var9.kind()) {
                                    case DATA:
                                        var17 = new byte[8];
                                        var14 = 0;

                                        while(true) {
                                            if (var14 >= 8) {
                                                break label74;
                                            }

                                            var17[var14] = 48;
                                            ++var14;
                                        }
                                    case MESSAGE:
                                        var17 = new byte[2];

                                        for(var14 = 0; var14 < 2; ++var14) {
                                            var17[var14] = 48;
                                        }
                                    default:
                                        break label74;
                                }
                            case VO:
                                var10 = TelegramUtil.getObjectFromField(var8);
                                var16 = TelegramUtil.getPacketSize(var10);
                                var17 = this.convertObject2Bytes(var10, this.limited);
                                break;
                            case BYTES:
                                var16 = var9.length();
                                var17 = new byte[var16];
                        }

                        if (var17 != null) {
                            var3.put(var17);
                        }
                    } else {
                        byte[] var18 = this.appendSerializedField(var10, var8);
                        if (var18 != null) {
                            var3.put(var18);
                        }
                    }
                } catch (Exception var15) {
                    TelegramNestedRuntimeException var12 = new TelegramNestedRuntimeException(var15.toString());
                    var12.setFieldName(var8.getName());
                    var12.setFtype(var9.type().getTypeName());
                    var12.setObjName(var1.getClass().getName());
                    var12.setParser("ByteEncoder");
                    var12.setStackTrace(var15.getStackTrace());
                    throw var12;
                }
            }

            return var3 != null ? var3.array() : null;
        }
    }

    private byte[] appendSerializedField(Object var1, Field var2) throws Exception {
        byte[] var3 = null;
        Type var4 = var2.getGenericType();
        String var5;
        if (TelegramUtil.isPrimitiveType(var4)) {
            var5 = var4.getTypeName();
            if ("int".equals(var5) && TelegramUtil.verifyIntegerNumber(var1, var2, "ByteEncoder")) {
                var3 = this.convertStringToBytes(((Integer)var1).toString(), var2);
            }

            if ("short".equals(var5) && TelegramUtil.verifyShortNumber(var1, var2, "ByteEncoder")) {
                var3 = this.convertStringToBytes(((Short)var1).toString(), var2);
            }

            if ("long".equals(var5) && TelegramUtil.verifyLongNumber(var1, var2, "ByteEncoder")) {
                var3 = this.convertStringToBytes(((Long)var1).toString(), var2);
            }

            String var6;
            if ("float".equals(var5)) {
                var6 = TelegramUtil.getStringFromDecimalNumberRound(var1, var2);
                if (TelegramUtil.verifyFloatNumber(Float.parseFloat(var6), var2, "ByteEncoder")) {
                    var3 = this.convertStringToBytes(var6, var2);
                }
            }

            if ("double".equals(var5)) {
                var6 = TelegramUtil.getStringFromDecimalNumberRound(var1, var2);
                if (TelegramUtil.verifyDoubleNumber(Double.parseDouble(var6), var2, "ByteEncoder")) {
                    var3 = this.convertStringToBytes(var6, var2);
                }
            }
        } else if (var2.getAnnotation(FIELD.class).type() == FieldType.VO) {
            var3 = this.convertObject2Bytes(var1, this.limited);
        } else if (var2.getType().isAssignableFrom(Integer.class)) {
            if (TelegramUtil.verifyIntegerNumber(var1, var2, "ByteEncoder")) {
                var5 = ((Integer)var1).toString();
                var3 = this.convertStringToBytes(var5, var2);
            }
        } else if (var2.getType().isAssignableFrom(Short.class)) {
            if (TelegramUtil.verifyShortNumber(var1, var2, "ByteEncoder")) {
                var5 = ((Short)var1).toString();
                var3 = this.convertStringToBytes(var5, var2);
            }
        } else if (var2.getType().isAssignableFrom(Long.class)) {
            if (TelegramUtil.verifyLongNumber(var1, var2, "ByteEncoder")) {
                var5 = ((Long)var1).toString();
                var3 = this.convertStringToBytes(var5, var2);
            }
        } else if (var2.getType().isAssignableFrom(Float.class)) {
            var5 = TelegramUtil.getStringFromDecimalNumberRound(var1, var2);
            if (TelegramUtil.verifyFloatNumber(Float.valueOf(var5), var2, "ByteEncoder")) {
                var3 = this.convertStringToBytes(var5, var2);
            }
        } else if (var2.getType().isAssignableFrom(Double.class)) {
            var5 = TelegramUtil.getStringFromDecimalNumberRound(var1, var2);
            if (TelegramUtil.verifyDoubleNumber(Double.valueOf(var5), var2, "ByteEncoder")) {
                var3 = this.convertStringToBytes(var5, var2);
            }
        } else if (var2.getType().isAssignableFrom(BigDecimal.class)) {
            var5 = TelegramUtil.getStringFromDecimalNumberRound(var1, var2);
            if (TelegramUtil.verifyDoubleNumber(Double.parseDouble(var5), var2, "ByteEncoder")) {
                var3 = this.convertStringToBytes(var5, var2);
            }
        } else if (var2.getType().isAssignableFrom(String.class)) {
            var3 = this.convertStringToBytes(var1, var2);
        } else if (var2.getType().isAssignableFrom(byte[].class)) {
            var3 = (byte[]) var1;
        } else {
            FIELD var14;
            if (var2.getType().isAssignableFrom(List.class)) {
                var14 = var2.getAnnotation(FIELD.class);
                int var13 = TelegramUtil.getPacketSize((List)var1);
                int var7 = 0;
                if (var1 != null) {
                    var7 = ((List)var1).size();
                }

                ByteBuffer var8;
                var8 = null;
                int var10;
                label105:
                switch (var14.kind()) {
                    case DATA:
                        byte var9 = 8;
                        var8 = ByteBuffer.allocate(var13 + var9);
                        var8.put(TelegramUtil.lpadString2Byte((Integer.valueOf(var7)).toString(), var9, "0", this.charSet));
                        if (this.limited && var1 != null) {
                            var10 = ((List)var1).size();
                            if ((long)var10 > 10000L) {
                                TelegramNestedRuntimeException var17 = new TelegramNestedRuntimeException("NumberFormat Exception");
                                var17.setMsg("Data Count [" + var10 + "] is over Maximuim [" + 10000L + "]");
                                throw var17;
                            }
                        }

                        Iterator var15 = ((List)var1).iterator();

                        while(true) {
                            if (!var15.hasNext()) {
                                break label105;
                            }

                            Object var16 = var15.next();
                            var8.put(this.convertObject2Bytes(var16, this.limited));
                        }
                    case MESSAGE:
                        var10 = 2;
                        var8 = ByteBuffer.allocate(var13 + var10);
                        var8.put(TelegramUtil.lpadString2Byte((Integer.valueOf(var7)).toString(), var10, "0", this.charSet));
                        Iterator var11 = ((List)var1).iterator();

                        while(var11.hasNext()) {
                            Object var12 = var11.next();
                            var8.put(this.convertObject2Bytes(var12, this.limited));
                        }
                }

                var3 = var8.array();
            } else {
                var14 = var2.getAnnotation(FIELD.class);
                switch (var14.type()) {
                    case VO:
                        var3 = this.convertObject2Bytes(var1, this.limited);
                }
            }
        }

        return var3;
    }

    private byte[] convertStringToBytes(Object var1, Field var2) throws Exception {
        byte[] var3 = null;
        FIELD var4 = var2.getAnnotation(FIELD.class);
        if (var1 == null) {
            return var3;
        } else {
            String var5 = var1 != null ? (String)var1 : "";
            int var6 = var4.length();
            int var7 = 0;
            if (var4.type() == FieldType.NUMBER) {
                DATATYPE var8 = var2.getAnnotation(DATATYPE.class);
                var6 += var8.sign_length();
                var6 += var8.point_length();
                var7 = var8.decimal();
            }

            int var10 = var5.getBytes(this.charSet).length;
            if (var10 > var6) {
                byte[] var9 = var5.getBytes(this.charSet);
                var3 = (new String(var9, 0, var6)).getBytes(this.charSet);
            } else {
                switch (var4.type()) {
                    case STRING:
                        var3 = TelegramUtil.rpadString2Byte(var5, var6, " ", this.charSet);
                        break;
                    case NUMBER:
                        var3 = TelegramUtil.lpadString2ByteWithDecimal(var5, var6, "0", this.charSet, var7);
                }
            }

            return var3;
        }
    }
}
