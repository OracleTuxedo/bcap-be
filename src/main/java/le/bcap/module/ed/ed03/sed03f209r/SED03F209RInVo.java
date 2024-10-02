package le.bcap.module.ed.ed03.sed03f209r;

import lombok.*;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SED03F209RInVo {
    @FIELD(kind=Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String whous_cd;

}
