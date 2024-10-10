package maas.bcap.module.ac.ac06.sac06f245r;

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
public class SAC06F245RInVo {
    @FIELD(kind = Kind.DATA, length = 4096, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String input_from_fep;
}
