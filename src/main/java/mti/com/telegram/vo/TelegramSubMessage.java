package mti.com.telegram.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramSubMessage {
    @FIELD(
            kind = Kind.MESSAGE,
            length = 100,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String subMsg;

    public TelegramSubMessage() {
    }

    public String getSubMsg() {
        return this.subMsg;
    }

    public void setSubMsg(String var1) {
        this.subMsg = var1;
    }

    public String toString() {
        return "TelegramSubMessage [subMsg=" + this.subMsg + "]";
    }
}
