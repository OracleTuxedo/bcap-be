package maas.bcap.module.mc.mc04.smc04v042r;
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
public class SMC04V042RInVo {
    @FIELD(kind = Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String supics_trns_tp_seq_no;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String supics_trns_tp_cd;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String inqr_strt_date;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String inqr_end_date;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long page_size;

    @FIELD(kind = Kind.DATA, length = 256, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String next_key_val;
}
