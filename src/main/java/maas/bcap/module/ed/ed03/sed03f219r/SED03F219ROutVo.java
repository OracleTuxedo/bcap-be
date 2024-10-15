package maas.bcap.module.ed.ed03.sed03f219r;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maas.bcap.module.ed.ed03.sed03f219r.SED03F219RSub1Vo;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SED03F219ROutVo {

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String cont_seq_no;

    @FIELD(kind = Kind.DATA, length = 30, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String agnc_cont_no;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String odr_no;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_cd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String divs_odr_option_cd;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prem_exam_yn;

    @FIELD(kind = Kind.DATA, length = 14, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String exam_dttm;

    @FIELD(kind = Kind.DATA, length = 300, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String exam_pl;

    @FIELD(kind = Kind.DATA, length = 7, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.DECIMAL, decimal = 4, sign_length = 1, point_length = 1)
    public double exam_rt;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String exam_rslt_yn;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String exam_expi_date;

    @FIELD(kind = Kind.DATA, length = 256, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String next_key_val;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long all_cnt;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long grid_cnt;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SED03F219RSub1Vo> sub1_vo;
}
