package maas.bcap.module.ed.ed03.sed03f075r;

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
public class SED03F075RInVo {
    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long page_size;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String next_key;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_tp_cd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_stat_cd;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String inqr_cond_cd;
}
