package maas.bcap.module.ac.ac20.sac20f002u;

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
public class SAC20F002UInVo {

    @FIELD(kind = Kind.DATA, length = 19, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_ref_no;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_proc_rslt_cd;
}
