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

    public TelegramNestedRuntimeException(String var1) {
        super(var1);
        this.msg = var1;
    }

    public TelegramNestedRuntimeException(String var1, Throwable var2) {
        super(var1, var2);
        this.msg = var1;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String var1) {
        this.fieldName = var1;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String var1) {
        this.msg = var1;
    }

    public long getPointer() {
        return this.pointer;
    }

    public void setPointer(long var1) {
        this.pointer = var1;
    }

    public String getFtype() {
        return this.ftype;
    }

    public void setFtype(String var1) {
        this.ftype = var1;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String var1) {
        this.method = var1;
    }

    public String getObjName() {
        return this.objName;
    }

    public void setObjName(String var1) {
        this.objName = var1;
    }

    public String getParser() {
        return this.parser;
    }

    public void setParser(String var1) {
        this.parser = var1;
    }

    public String toString() {
        return "TelegramNestedRuntimeException [fieldName=" + this.fieldName + ", msg=" + this.msg + ", ftype=" + this.ftype + ", method=" + this.method + ", pointer=" + this.pointer + ", objName=" + this.objName + ", parser=" + this.parser + "]";
    }
}
