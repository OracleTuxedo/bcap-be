package maas.bcap.module.ac.ac04.sac04f231r;

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
public class SAC04F231ROutVo {

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String next_page_exist_yn;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAC04F231ROutSub1Vo> sub1Vo;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAC04F231ROutSub2Vo> sub2Vo;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAC04F231ROutSub3Vo> sub3Vo;

}
