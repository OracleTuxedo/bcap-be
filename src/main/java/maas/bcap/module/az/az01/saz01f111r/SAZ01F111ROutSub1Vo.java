package maas.bcap.module.az.az01.saz01f111r;

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
public class SAZ01F111ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tx_code;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tx_name;

}
