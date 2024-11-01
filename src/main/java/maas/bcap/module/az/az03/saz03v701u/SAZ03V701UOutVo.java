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
public class SAZ03V701UOutVo {

    @FIELD(kind=Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_id;

    @FIELD(kind=Kind.DATA, length = 50, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_nm;

    @FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String paswd_chg_yn;	// When to change your password "Y"

    @FIELD(kind=Kind.DATA, length = 13, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String emp_no;		// mobile is employee Number

    @FIELD(kind=Kind.DATA, length = 30, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_ino;		// User identification Number

    @FIELD(kind=Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_ctgo_cd;		// authority

    @FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String adm_usr_yn;		//'Y' MID EDIT.enable = true         'N' enable = false

}
