package mti.com.telegram.vo;

import java.util.ArrayList;
import java.util.List;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    public List<TelegramSubMessage> list = new ArrayList<TelegramSubMessage>();

}
