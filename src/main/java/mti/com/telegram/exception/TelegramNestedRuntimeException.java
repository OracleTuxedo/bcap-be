package mti.com.telegram.exception;

import org.springframework.core.NestedRuntimeException;

public class TelegramNestedRuntimeException extends NestedRuntimeException {
    private static final long serialVersionUID = 3822354226516829667L;

    private String fieldName;

    private String msg;

    private String ftype;

    private String method;

    private long pointer;

    private String objName;

    private String parser;

    public TelegramNestedRuntimeException(String paramString) {
        super(paramString);
        this.msg = paramString;
    }

    public TelegramNestedRuntimeException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
        this.msg = paramString;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String paramString) {
        this.fieldName = paramString;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String paramString) {
        this.msg = paramString;
    }

    public long getPointer() {
        return this.pointer;
    }

    public void setPointer(long paramLong) {
        this.pointer = paramLong;
    }

    public String getFtype() {
        return this.ftype;
    }

    public void setFtype(String paramString) {
        this.ftype = paramString;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String paramString) {
        this.method = paramString;
    }

    public String getObjName() {
        return this.objName;
    }

    public void setObjName(String paramString) {
        this.objName = paramString;
    }

    public String getParser() {
        return this.parser;
    }

    public void setParser(String paramString) {
        this.parser = paramString;
    }

    public String toString() {
        return "TelegramNestedRuntimeException [fieldName=" + this.fieldName + ", msg=" + this.msg + ", ftype="
                + this.ftype + ", method=" + this.method + ", pointer=" + this.pointer + ", objName=" + this.objName
                + ", parser=" + this.parser + "]";
    }
}