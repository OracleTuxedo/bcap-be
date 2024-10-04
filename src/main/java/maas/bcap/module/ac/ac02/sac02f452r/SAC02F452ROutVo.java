package maas.bcap.module.ac.ac02.sac02f452r;

import java.util.List;

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
public class SAC02F452ROutVo {

    @FIELD(kind=Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.INT, decimal=0)
    public long tot_cnt;

    @FIELD(kind=Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAC02F452ROutSub1Vo> sub1Vos;

}
