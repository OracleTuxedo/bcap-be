package mti.mc.mc07;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMC07V004ROutSub2Vo {

    @FIELD(kind = Kind.DATA, length = 30, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mb_co_nm;

    @FIELD(kind = Kind.DATA, length = 30, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String trns_nm;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String brnd_nm;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String on_off_cl;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String rt_amt_cl;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.DECIMAL, decimal = 2, sign_length = 1, point_length = 1)
    public double appl_fee;

}
