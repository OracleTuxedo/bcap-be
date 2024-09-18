package mti.com.telegram.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramOut {
    @FIELD(
            kind = Kind.DATA,
            length = 0,
            type = FieldType.VO,
            trim = TrimType.NONE
    )
    public TelegramHeader header;
    @FIELD(
            kind = Kind.MESSAGE,
            length = 0,
            type = FieldType.VO,
            trim = TrimType.NONE
    )
    public TelegramMessage message;
    @FIELD(
            kind = Kind.DATA,
            length = 0,
            type = FieldType.VO,
            trim = TrimType.NONE
    )
    public TelegramDataOut data;
    @FIELD(
            kind = Kind.TAIL,
            length = 0,
            type = FieldType.VO,
            trim = TrimType.NONE
    )
    public TelegramTail tail;

    public TelegramOut() {
    }

    public TelegramHeader getHeader() {
        return this.header;
    }

    public void setHeader(TelegramHeader var1) {
        this.header = var1;
    }

    public TelegramMessage getMessage() {
        return this.message;
    }

    public void setMessage(TelegramMessage var1) {
        this.message = var1;
    }

    public TelegramDataOut getData() {
        return this.data;
    }

    public void setData(TelegramDataOut var1) {
        this.data = var1;
    }

    public TelegramTail getTail() {
        return this.tail;
    }

    public void setTail(TelegramTail var1) {
        this.tail = var1;
    }

    public String toString() {
        return "TelegramOut [header=" + this.header + ", message=" + this.message + ", data=" + this.data + ", tail=" + this.tail + "]";
    }
}
