package mti.com.telegram.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramIn<V> {
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
    public TelegramInData<V> data;
    @FIELD(
        kind = Kind.TAIL,
        length = 2,
        type = FieldType.STRING,
        trim = TrimType.RTRIM
    )
    public String tail = "@@";

}
