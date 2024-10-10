package mti.com.telegram.mapping;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.telegram.util.TelegramUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.List;

public class ByteEncoder {
    private String charSet = "UTF-8";
    private boolean limited = true;

    public ByteEncoder() {
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public byte[] convertObjectToBytes(Object obj, boolean limitedFlag) throws Exception {
        this.limited = limitedFlag;
        if (obj == null) {
            return null;
        }

        int packetSize = TelegramUtil.getPacketSize(obj);
        ByteBuffer byteBuffer = ByteBuffer.allocate(packetSize);
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            FIELD fieldAnnotation = field.getAnnotation(FIELD.class);
            Object fieldValue = field.get(obj);

            try {
                if (fieldValue == null) {
                    byte[] placeholderBytes = createPlaceholderBytes(fieldAnnotation, field);
                    if (placeholderBytes != null) {
                        byteBuffer.put(placeholderBytes);
                    }
                } else {
                    byte[] serializedField = appendSerializedField(fieldValue, field);
                    if (serializedField != null) {
                        byteBuffer.put(serializedField);
                    }
                }
            } catch (Exception e) {
                TelegramNestedRuntimeException ex = new TelegramNestedRuntimeException(e.getMessage());
                ex.setFieldName(field.getName());
                ex.setFtype(fieldAnnotation.type().getTypeName());
                ex.setObjName(obj.getClass().getName());
                ex.setParser("ByteEncoder");
                ex.setStackTrace(e.getStackTrace());
                throw ex;
            }
        }

        return byteBuffer.array();
    }

    private byte[] createPlaceholderBytes(FIELD fieldAnnotation, Field field) {
        int length = fieldAnnotation.length();
        byte[] placeholderBytes = null;

        switch (fieldAnnotation.type()) {
            case STRING:
                placeholderBytes = new byte[length];
                for (int i = 0; i < length; i++) {
                    placeholderBytes[i] = ' ';
                }
                break;

            case NUMBER:
                DATATYPE dataType = field.getAnnotation(DATATYPE.class);
                length += dataType.sign_length() + dataType.point_length();
                placeholderBytes = new byte[length];
                for (int i = 0; i < length; i++) {
                    placeholderBytes[i] = '0';
                }
                break;

            case LIST:
                switch (fieldAnnotation.kind()) {
                    case DATA:
                        placeholderBytes = new byte[8];
                        for (int i = 0; i < 8; i++) {
                            placeholderBytes[i] = '0';
                        }
                        break;
                    case MESSAGE:
                        placeholderBytes = new byte[2];
                        for (int i = 0; i < 2; i++) {
                            placeholderBytes[i] = '0';
                        }
                        break;
                    default:
                        break;
                }
                break;

            case VO:
                try {
                    Object nestedObject = TelegramUtil.getObjectFromField(field);
                    placeholderBytes = convertObjectToBytes(nestedObject, limited);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case BYTES:
                placeholderBytes = new byte[length];
                break;

            default:
                break;
        }

        return placeholderBytes;
    }

    private byte[] appendSerializedField(Object fieldValue, Field field) throws Exception {
        byte[] serializedBytes = null;
        Type fieldType = field.getGenericType();

        if (TelegramUtil.isPrimitiveType(fieldType)) {
            serializedBytes = handlePrimitiveField(fieldValue, field);
        } else if (field.getAnnotation(FIELD.class).type() == FieldType.VO) {
            serializedBytes = convertObjectToBytes(fieldValue, limited);
        } else if (List.class.isAssignableFrom(field.getType())) {
            serializedBytes = handleListField(fieldValue, field);
        } else {
            serializedBytes = handleNonPrimitiveField(fieldValue, field);
        }

        return serializedBytes;
    }

    private byte[] handlePrimitiveField(Object fieldValue, Field field) throws Exception {
        String typeName = field.getGenericType().getTypeName();
        switch (typeName) {
            case "int":
                return convertStringToBytes(Integer.toString((int) fieldValue), field);
            case "short":
                return convertStringToBytes(Short.toString((short) fieldValue), field);
            case "long":
                return convertStringToBytes(Long.toString((long) fieldValue), field);
            case "float":
            case "double":
                String decimalValue = TelegramUtil.getStringFromDecimalNumberRound(fieldValue, field);
                return convertStringToBytes(decimalValue, field);
            default:
                return null;
        }
    }

    private byte[] handleListField(Object fieldValue, Field field) throws Exception {
        // FIELD fieldAnnotation = field.getAnnotation(FIELD.class);
        int packetSize = TelegramUtil.getPacketSize((List<?>) fieldValue);
        int listSize = ((List<?>) fieldValue).size();
        ByteBuffer buffer = ByteBuffer.allocate(packetSize + 8);

        buffer.put(TelegramUtil.lpadString2Byte(Integer.toString(listSize), 8, "0", charSet));
        for (Object item : (List<?>) fieldValue) {
            buffer.put(convertObjectToBytes(item, limited));
        }

        return buffer.array();
    }

    private byte[] handleNonPrimitiveField(Object fieldValue, Field field) throws Exception {
        if (fieldValue instanceof BigDecimal) {
            String decimalValue = TelegramUtil.getStringFromDecimalNumberRound(fieldValue, field);
            return convertStringToBytes(decimalValue, field);
        } else if (fieldValue instanceof String) {
            return convertStringToBytes((String) fieldValue, field);
        } else if (fieldValue instanceof byte[]) {
            return (byte[]) fieldValue;
        }
        return null;
    }

    private byte[] convertStringToBytes(String value, Field field) throws Exception {
        FIELD fieldAnnotation = field.getAnnotation(FIELD.class);
        int length = fieldAnnotation.length();
        byte[] resultBytes;

        if (fieldAnnotation.type() == FieldType.NUMBER) {
            DATATYPE dataType = field.getAnnotation(DATATYPE.class);
            length += dataType.sign_length() + dataType.point_length();
            resultBytes = TelegramUtil.lpadString2ByteWithDecimal(value, length, "0", charSet, dataType.decimal());
        } else {
            resultBytes = TelegramUtil.rpadString2Byte(value, length, " ", charSet);
        }

        return resultBytes;
    }
}
