package mti.com.telegram.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramInList {
    @FIELD(
            kind = Kind.HEADER,
            length = 0,
            type = FieldType.VO,
            trim = TrimType.NONE
    )
    public TelegramHeader header;
    @FIELD(
            kind = Kind.DATA,
            length = 0,
            type = FieldType.VO,
            trim = TrimType.NONE
    )
    public TelegramDataList data;
    @FIELD(
            kind = Kind.TAIL,
            length = 2,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String tail = "@@";

    public TelegramInList() {
    }

    public TelegramHeader getHeader() {
        return this.header;
    }

    public void setHeader(TelegramHeader var1) {
        this.header = var1;
    }

    public TelegramDataList getData() {
        return this.data;
    }

    public void setData(TelegramDataList var1) {
        this.data = var1;
    }

    public String toString() {
        return "TelegramInList [header=" + this.header + ", data=" + this.data + "]";
    }
}
