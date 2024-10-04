package maas.bcap.module.mc.mc07.smc07v005r;

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
public class SMC07V005RInVo {

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String inqr_strt_date;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String inqr_end_date;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String npwp;

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String aplc_seq_no;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long page_no;
}
