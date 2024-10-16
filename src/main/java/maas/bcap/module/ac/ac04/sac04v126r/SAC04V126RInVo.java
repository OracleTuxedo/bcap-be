package maas.bcap.module.ac.ac04.sac04v126r;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SAC04V126RInVo {

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String biz_clcd;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long row_no;

    @FIELD(kind = Kind.DATA, length = 48, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mb_grup_id;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_date;

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mid;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String acq_mb_no;

    @FIELD(kind = Kind.DATA, length = 1000, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String re_proc_ctnts;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.DECIMAL, decimal = 2, sign_length = 1, point_length = 1)
    public double rl_pmt_amt;

    @FIELD(kind = Kind.DATA, length = 12, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_batch_no;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_seq_no;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tp_cd;

}
