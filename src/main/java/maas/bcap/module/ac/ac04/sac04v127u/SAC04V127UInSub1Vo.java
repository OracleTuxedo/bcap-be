package maas.bcap.module.ac.ac04.sac04v127u;

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
public class SAC04V127UInSub1Vo {

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long row_no;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_date;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String acq_mb_no;

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mid;

    @FIELD(kind = Kind.DATA, length = 12, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_batch_no;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long pmt_seq_no;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String reg_date;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long reg_seq_no;

}
