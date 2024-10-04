package maas.bcap.module.ed.ed03.sed03f209r;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SED03F209ROutVo {

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SED03F209ROutSub1Vo> sub1Vo;

}
