package mti.com.telegram.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramDataOut {
    @FIELD(
            kind = Kind.DATA,
            length = 1,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String dataType;
    @FIELD(
            kind = Kind.DATA,
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
            kind = Kind.DATA,
            length = 21,
            type = FieldType.STRING,
            trim = TrimType.RTRIM
    )
    public String reserved;
    @FIELD(
            kind = Kind.DATA,
            length = 0,
            type = FieldType.VO,
            trim = TrimType.NONE
    )
    public Object data;

    public TelegramDataOut() {
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String var1) {
        this.dataType = var1;
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

    public Object getData() {
        return this.data;
    }

    public void setData(Object var1) {
        this.data = var1;
    }

    public String toString() {
        return "TelegramDataOut [dataType=" + this.dataType + ", length=" + this.length + ", reserved=" + this.reserved + ", data=" + this.data + "]";
    }
}
