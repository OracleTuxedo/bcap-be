package maas.bcap.module.ac.ac06.sac06v610r;

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
public class SAC06V610RInVo {
    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mid;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String acq_mb_no;

    @FIELD(kind = Kind.DATA, length = 7, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String acct_mgmt_bk_cd;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_acct_no;
}
