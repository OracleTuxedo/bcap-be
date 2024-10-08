package mti.com.telegram.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramInData<V> {
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
    public V data;

}
