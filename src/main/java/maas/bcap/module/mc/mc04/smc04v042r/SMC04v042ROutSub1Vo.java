package maas.bcap.module.mc.mc04.smc04v042r;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UOutSub1Vo;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMC04v042ROutSub1Vo {
    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long row_no;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_incap_proc_stat_cd;

    @FIELD(kind = Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String proc_rslt_cd;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String msg_cd;
}
