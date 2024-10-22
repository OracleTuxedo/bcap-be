package maas.bcap.module.az.az03.saz03v701u;

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
public class SAZ03V701UInVo {

    @FIELD(kind=Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_id;

    @FIELD(kind=Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_paswd;

    @FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String chnl_clcd;	//  1:WEB 2:Mobile

    @FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String req_tp;		//  I:Login O:Logout

    @FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String admin_yn;		//  Admin Login YN
}
