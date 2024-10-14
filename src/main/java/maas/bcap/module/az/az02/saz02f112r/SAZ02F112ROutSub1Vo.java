package maas.bcap.module.az.az02.saz02f112r;

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
public class SAZ02F112ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dtl_cd_id;

    @FIELD(kind = Kind.DATA, length = 256, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dtl_cd_nm;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val1;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val2;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val3;

    @FIELD(kind = Kind.DATA, length = 1000, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String cd_expl;
}
