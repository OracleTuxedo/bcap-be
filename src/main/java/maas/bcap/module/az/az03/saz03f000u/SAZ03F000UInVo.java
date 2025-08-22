package maas.bcap.module.az.az03.saz03f000u;

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
public class SAZ03F000UInVo {

    /*
     * I = MAAS Login
     * M = MMP User Login
     * B = Member Bank User Login
     * O = MAAS Logout
     */
    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_conn_clcd;

    @FIELD(kind = Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_id;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_paswd;

}

