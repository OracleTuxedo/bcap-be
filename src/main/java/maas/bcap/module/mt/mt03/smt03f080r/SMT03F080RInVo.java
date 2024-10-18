package maas.bcap.module.mt.mt03.smt03f080r;

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
public class SMT03F080RInVo {
    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String req_clcd;

    @FIELD(kind = Kind.DATA, length = 60, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String srch_val;
}
