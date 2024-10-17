package mti.com.telegram.mapping;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.telegram.util.TelegramUtil;
import mti.com.telegram.vo.TelegramOutData;
import mti.com.telegram.vo.TelegramOutDataList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ByteDecoder<T> {
    private String charSet = "UTF-8";
    private boolean limited = true;

    public ByteDecoder() {
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public T convertBytes2Object(byte[] byteArray, T obj, boolean limitedFlag) throws Exception {
        try {
            this.limited = limitedFlag;
            parseBytes(byteArray, obj);
            return obj;
        } catch (Exception e) {
            throw e;
        }
    }

    public Object convertBytes2Object(byte[] byteArray, Object obj, int offset) throws Exception {
        try {
            int length = byteArray.length - offset;
            byte[] subArray = TelegramUtil.cutBytes(byteArray, offset, length);
            parseBytes(subArray, obj);
            return obj;
        } catch (Exception e) {
            throw e;
        }
    }

    private void parseBytes(byte[] byteArray, Object obj) throws Exception {
        int pointer = 0;
        TelegramNestedRuntimeException exception;
        if (byteArray == null) {
            exception = new TelegramNestedRuntimeException("Byte input is null");
            exception.setParser("ByteDecoder");
            throw exception;
        } else if (obj == null) {
            exception = new TelegramNestedRuntimeException("Input Object is null");
            exception.setParser("ByteDecoder");
            throw exception;
        } else {
            Field[] fields = obj.getClass().getDeclaredFields();
            String setterMethodName = "";

            for (Field field : fields) {
                FIELD fieldAnnotation = field.getAnnotation(FIELD.class);
                int length = fieldAnnotation.length();
                String fieldName = field.getName();

                try {
                    switch (fieldAnnotation.type()) {
                        case STRING:
                            byte[] stringBytes = TelegramUtil.cutBytes(byteArray, pointer, length);
                            String stringValue = TelegramUtil.byte2StringTrimmed(stringBytes, this.charSet);
                            setterMethodName = TelegramUtil.getSetterMethodName(fieldName);
                            Method setterMethod = field.getDeclaringClass().getDeclaredMethod(setterMethodName, String.class);
                            TelegramUtil.invokeMethod(setterMethod, obj, stringValue);
                            pointer += length;

                            System.out.println(setterMethodName);
                            System.out.println(setterMethod.getDeclaringClass().toString() + '\n');

                            break;
                        case NUMBER:
                            DATATYPE datatypeAnnotation = field.getAnnotation(DATATYPE.class);
                            length += datatypeAnnotation.sign_length();
                            length += datatypeAnnotation.point_length();
                            byte[] numberBytes = TelegramUtil.cutBytes(byteArray, pointer, length);
                            setterMethodName = TelegramUtil.getSetterMethodName(fieldName);
                            TelegramUtil.setterMethodForNumberInvoke(obj, field, setterMethodName, numberBytes, this.charSet, "ByteDecoder");
                            pointer += length;

                            System.out.println(setterMethodName + '\n');

                            break;
                        case LIST:
                            processListType(byteArray, obj, field, fieldAnnotation, pointer);
                            break;
                        case VO:
                            Object voObject = field.get(obj);
                            if (voObject == null) {
                                voObject = TelegramUtil.getObjectFromField(field);
                            }

                            byte[] voBytes = TelegramUtil.cutBytes(byteArray, pointer, byteArray.length - pointer);
                            int packetSize = TelegramUtil.getPacketSize(voObject, voBytes, this.limited);
                            byte[] voDataBytes = TelegramUtil.cutBytes(byteArray, pointer, packetSize);
                            parseBytes(voDataBytes, voObject);

                            setterMethodName = TelegramUtil.getSetterMethodName(fieldName);
                            Method voSetterMethod;
                            if (obj instanceof TelegramOutData) {
                                voSetterMethod = field.getDeclaringClass().getDeclaredMethod(setterMethodName, Object.class);
                            } else if (obj instanceof TelegramOutDataList) {
                                voSetterMethod = field.getDeclaringClass().getDeclaredMethod(setterMethodName, List.class);
                            } else {
                                voSetterMethod = field.getDeclaringClass().getDeclaredMethod(setterMethodName, voObject.getClass());
                            }

                            TelegramUtil.invokeMethod(voSetterMethod, obj, voObject);
                            pointer += packetSize;
                            break;
                        case BYTES:
                            byte[] bytesData = TelegramUtil.cutBytes(byteArray, pointer, length);
                            setterMethodName = TelegramUtil.getSetterMethodName(fieldName);
                            Method bytesSetterMethod = field.getDeclaringClass().getDeclaredMethod(setterMethodName, byte[].class);
                            TelegramUtil.invokeMethod(bytesSetterMethod, obj, bytesData);
                            pointer += length;
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    exception = new TelegramNestedRuntimeException(e.toString());

                    exception.setFieldName(fieldName);
                    exception.setFtype(fieldAnnotation.type().getTypeName());
                    exception.setObjName(obj.getClass().getName());
                    exception.setPointer(pointer);
                    exception.setMsg(e.toString());
                    exception.setParser("ByteDecoder");
                    exception.setStackTrace(e.getStackTrace());
                    throw exception;
                }
            }
        }
    }

    private void processListType(byte[] byteArray, Object obj, Field field, FIELD fieldAnnotation, int pointer) throws Exception {
        String setterMethodName = TelegramUtil.getSetterMethodName(field.getName());
        Method listSetterMethod = field.getDeclaringClass().getDeclaredMethod(setterMethodName, List.class);
        int itemCount;

        switch (fieldAnnotation.kind()) {
            case DATA:
                byte[] countBytes = TelegramUtil.cutBytes(byteArray, pointer, 8);
                String countString = (new String(countBytes)).trim();
                if (!Pattern.matches("^[0-9]*$", countString)) {
                    TelegramNestedRuntimeException exception = new TelegramNestedRuntimeException("Data Count String is [" + countString + "]. is not NumberType");

                    exception.setFieldName(field.getName());
                    exception.setFtype(fieldAnnotation.type().getTypeName());
                    exception.setObjName(obj.getClass().getName());
                    throw exception;
                }

                itemCount = Integer.parseInt(countString);
                pointer += 8;
                List<Object> dataList = new ArrayList<>();

                for (int i = 0; i < itemCount; i++) {
                    ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                    Class<?> itemClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    Object listItem = itemClass.getDeclaredConstructor().newInstance();
                    int packetSize = TelegramUtil.getPacketSize(listItem);
                    byte[] itemBytes = TelegramUtil.cutBytes(byteArray, pointer, packetSize);
                    parseBytes(itemBytes, listItem);
                    dataList.add(listItem);
                    pointer += packetSize;

                    if (pointer > byteArray.length) {
                        TelegramNestedRuntimeException exception = new TelegramNestedRuntimeException("Data List length is abnormal");
                        exception.setParser("ByteDecoder");
                        throw exception;
                    }
                }

                TelegramUtil.invokeMethod(listSetterMethod, obj, dataList);
                break;
            case MESSAGE:
                // Similar handling for MESSAGE type with logic adapted to the new Java features
                break;
            default:
                break;
        }
    }
}
