package maas.bcap.module.ed.ed03.sed03f075r;

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
public class SED03F075ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_cd;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mnft_cd;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_nm;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_ctgo_cd;

    @FIELD(kind = Kind.DATA, length = 14, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String data_inp_dttm;
}
