package maas.bcap.module.az.az05.saz05f041u;

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
public class SAZ05F041UInSub1Vo {
    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String biz_clcd;

    @FIELD(kind = Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_id;

    @FIELD(kind = Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_mgr_usr_id;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_mgr_clcd;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String data_stat_cd;

}
