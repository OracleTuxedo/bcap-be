package maas.bcap.module.mc.mc02.smc02f042r;

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
public class SMC02F042RInSub1Vo {

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String brnd_clcd;

    @FIELD(kind = Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mcc_cd;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mcc_mcc_cd_nmcd;

    @FIELD(kind = Kind.DATA, length = 200, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mcc_cd_expl;

    @FIELD(kind = Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String grup_mcc_cd;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tcc_cd;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.DECIMAL, decimal = 2, sign_length = 1, point_length = 1)
    public double mer_mo_lim_amt;

    @FIELD(kind = Kind.DATA, length = 7, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.DOUBLE, decimal = 4, sign_length = 1, point_length = 1)
    public double auto_lim_moinc_std_non_rt;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long auto_lim_mo_moinc_cnt;

    @FIELD(kind = Kind.DATA, length = 7, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.DOUBLE, decimal = 4, sign_length = 1, point_length = 1)
    public double auto_liam_non_rt;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mcc_end_date;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String ins_able_yn;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String ofl_trns_able_yn;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String del_yn;
}
