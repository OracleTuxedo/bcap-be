package mti.ed.ed03.vo;

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
public class SED03F224ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_cd;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_nm;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_tp_cd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_ctgo_cd;

}
