package maas.bcap.module.ac.ac06.sac06f244r;

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
public class SAC06F244RInVo {
    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prem_chk_no;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prem_chk_nm;

    @FIELD(kind = Kind.DATA, length = 40, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String sql_id;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String purc_sttl_biz_clcd;

    @FIELD(kind = Kind.DATA, length = 256, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String next_key_val;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long page_size;
}
