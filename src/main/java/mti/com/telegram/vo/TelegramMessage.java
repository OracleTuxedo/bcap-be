package mti.com.telegram.vo;

import java.util.ArrayList;
import java.util.List;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramMessage {
    @FIELD(
            kind = Kind.MESSAGE,
            length = 1,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String kind;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 8,
            type = FieldType.NUMBER,
            trim = TrimType.LTRIM
    )
    @DATATYPE(
            type = NumberType.INT,
            decimal = 0
    )
    public int length;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 21,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String reserved;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 1,
            type = FieldType.NUMBER,
            trim = TrimType.LTRIM
    )
    @DATATYPE(
            type = NumberType.INT,
            decimal = 0
    )
    public int msgAttr;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 1,
            type = FieldType.NUMBER,
            trim = TrimType.LTRIM
    )
    @DATATYPE(
            type = NumberType.INT,
            decimal = 0
    )
    public int msgAlm;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 200,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String message;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 100,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String error_field_name;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 100,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String error_info;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 0,
            type = FieldType.LIST,
            trim = TrimType.NONE
    )
    public List<TelegramSubMessage> list = new ArrayList();

    public TelegramMessage() {
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String var1) {
        this.kind = var1;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int var1) {
        this.length = var1;
    }

    public String getReserved() {
        return this.reserved;
    }

    public void setReserved(String var1) {
        this.reserved = var1;
    }

    public int getMsgAttr() {
        return this.msgAttr;
    }

    public void setMsgAttr(int var1) {
        this.msgAttr = var1;
    }

    public int getMsgAlm() {
        return this.msgAlm;
    }

    public void setMsgAlm(int var1) {
        this.msgAlm = var1;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String var1) {
        this.message = var1;
    }

    public String getError_field_name() {
        return this.error_field_name;
    }

    public void setError_field_name(String var1) {
        this.error_field_name = var1;
    }

    public String getError_info() {
        return this.error_info;
    }

    public void setError_info(String var1) {
        this.error_info = var1;
    }

    public List<TelegramSubMessage> getList() {
        return this.list;
    }

    public void setList(List<TelegramSubMessage> var1) {
        this.list = var1;
    }

    public void insertSubMsg(TelegramSubMessage var1) {
        if (this.list == null) {
            this.list = new ArrayList();
        }

        this.list.add(var1);
    }

    public String toString() {
        return "TelegramMessage [kind=" + this.kind + ", length=" + this.length + ", reserved=" + this.reserved + ", msgAttr=" + this.msgAttr + ", msgAlm=" + this.msgAlm + ", message=" + this.message + ", error_field_name=" + this.error_field_name + ", error_info=" + this.error_info + ", list=" + this.list + "]";
    }
}
