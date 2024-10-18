package maas.bcap.module.az.az05.saz05f041u;

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
public class SAZ05F041UInVo {

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAZ05F041UInSub1Vo> sub1_vo;

}
