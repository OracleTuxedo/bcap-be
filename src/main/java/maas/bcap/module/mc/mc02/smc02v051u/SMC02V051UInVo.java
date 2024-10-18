package maas.bcap.module.mc.mc02.smc02v051u;

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
public class SMC02V051UInVo {
    @FIELD(kind = Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String transaction_no;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String proc_cd;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String system_trace;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String reg_date;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String reg_time;

    @FIELD(kind = Kind.DATA, length = 13, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String my_sls_id;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String npwp;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String ktpno;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_acct_no;

}
