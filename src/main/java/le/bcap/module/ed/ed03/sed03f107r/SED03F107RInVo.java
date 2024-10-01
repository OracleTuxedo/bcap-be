package le.bcap.module.ed.ed03.sed03f107r;

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
public class SED03F107RInVo {

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_tp_cd;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String sno;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String srl_stat_cd;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String srl_st_cd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_cd;

    @FIELD(kind = Kind.DATA, length = 22, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String icc_id;

}
