package maas.bcap.module.au.au01.sau01f730r;
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
public class SAU01F730ROutSub1Vo {
    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mb_no;

    @FIELD(kind = Kind.DATA, length = 19, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String bin_no;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String once_lim_amt;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String lim_appl_strt_date;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String lim_appl_end_date;

}
