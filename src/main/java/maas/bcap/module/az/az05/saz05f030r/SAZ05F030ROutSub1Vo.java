package maas.bcap.module.az.az05.saz05f030r;
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
public class SAZ05F030ROutSub1Vo {
    @FIELD(kind = Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String usr_id;

    @FIELD(kind = Kind.DATA, length = 13, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_req_chg_emp_no;

    @FIELD(kind = Kind.DATA, length = 60, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_req_chg_emp_nm;

    @FIELD(kind = Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_mgr_usr_id;

    @FIELD(kind = Kind.DATA, length = 13, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mgr_emp_no;

    @FIELD(kind = Kind.DATA, length = 60, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String emp_nm;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_mgr_clcd;
}
