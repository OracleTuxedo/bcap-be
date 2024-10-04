package le.bcap.module.mc.mc07.smc07v005r;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMC07V005ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String aplc_seq_no;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String npwp;

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mid;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_nm;

    @FIELD(kind = Kind.DATA, length = 60, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String rpsv_nm;

    @FIELD(kind = Kind.DATA, length = 200, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_addr;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String new_regt_date;

    @FIELD(kind = Kind.DATA, length = 30, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String aplc_proc_stat_nm;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apfm_fnl_conf_date;

}
