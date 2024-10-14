package maas.bcap.module.az.az02.saz02f120r;

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
public class SAZ02F120ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String msg_clcd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String lang_clcd;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String msg_id;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String data_stat_cd;

    @FIELD(kind = Kind.DATA, length = 512, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String msg_nm;

    @FIELD(kind = Kind.DATA, length = 512, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tip_msg_nm;
}
