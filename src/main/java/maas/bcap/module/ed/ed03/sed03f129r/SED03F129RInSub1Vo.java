package maas.bcap.module.ed.ed03.sed03f129r;

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
public class SED03F129RInSub1Vo {

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tid;
}
