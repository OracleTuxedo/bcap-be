package maas.bcap.module.az.az02.saz02f114r;

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
public class SAZ02F114ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String cmmn_cd_id;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dtl_cd_id;

    @FIELD(kind = Kind.DATA, length = 256, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String msg_nm;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long sort_seq;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String data_stat_cd;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val1;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val2;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val3;

    @FIELD(kind = Kind.DATA, length = 1000, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String cd_expl;
}
