package maas.bcap.module.ac.ac04.sac04v233R;

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

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SAC04V233ROutVo {

    @FIELD(kind = Kind.DATA, length = 60, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String rspv_nm;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String npwp;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_acct_bk_cd;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_acct_no;

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mid;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_nm;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_pmt_sttl_day_cd;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_pmt_cycl_cd;

    @FIELD(kind = Kind.DATA, length = 300, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_dtl_addr;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long tot_cnt;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAC04V233ROutSub1Vo> sub1Vo;
}
