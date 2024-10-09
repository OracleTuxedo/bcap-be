package maas.bcap.module.ac.ac04.sac04v127u;

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
public class SAC04V127UOutVo {

    @FIELD(kind = Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String proc_rslt_cd;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAC04V127UOutSub1Vo> sub1Vo;

}
