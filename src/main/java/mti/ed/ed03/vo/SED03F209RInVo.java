package mti.ed.ed03.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

public class SED03F209RInVo {
    @FIELD(kind=Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String whous_cd;

    public String getWhous_cd() {
        return whous_cd;
    }

    public void setWhous_cd(String whous_cd) {
        this.whous_cd = whous_cd;
    }

    @Override
    public String toString() {
        return "SED03F209RInVo [whous_cd=" + whous_cd + "]";
    }

}
