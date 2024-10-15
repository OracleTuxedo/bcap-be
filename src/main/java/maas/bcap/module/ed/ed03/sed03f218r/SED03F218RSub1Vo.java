package maas.bcap.module.ed.ed03.sed03f218r;


import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;


public class SED03F218RSub1Vo {

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

    @FIELD(kind = Kind.DATA, length = 14, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String exam_dttm;

    @FIELD(kind = Kind.DATA, length = 300, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String exam_pl;
}

