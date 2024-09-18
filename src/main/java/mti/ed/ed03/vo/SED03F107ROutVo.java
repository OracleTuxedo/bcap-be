package mti.ed.ed03.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

public class SED03F107ROutVo {

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long count;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String sno;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String whous_cd;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String rack_no;

    @FIELD(kind = Kind.DATA, length = 22, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String icc_id;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String sim_no;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String srl_stat_cd;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String srl_st_cd;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String srl_loca_cd;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String vend_no;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_cd;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String prd_nm;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getWhous_cd() {
        return whous_cd;
    }

    public void setWhous_cd(String whous_cd) {
        this.whous_cd = whous_cd;
    }

    public String getRack_no() {
        return rack_no;
    }

    public void setRack_no(String rack_no) {
        this.rack_no = rack_no;
    }

    public String getIcc_id() {
        return icc_id;
    }

    public void setIcc_id(String icc_id) {
        this.icc_id = icc_id;
    }

    public String getSim_no() {
        return sim_no;
    }

    public void setSim_no(String sim_no) {
        this.sim_no = sim_no;
    }

    public String getSrl_stat_cd() {
        return srl_stat_cd;
    }

    public void setSrl_stat_cd(String srl_stat_cd) {
        this.srl_stat_cd = srl_stat_cd;
    }

    public String getSrl_st_cd() {
        return srl_st_cd;
    }

    public void setSrl_st_cd(String srl_st_cd) {
        this.srl_st_cd = srl_st_cd;
    }

    public String getSrl_loca_cd() {
        return srl_loca_cd;
    }

    public void setSrl_loca_cd(String srl_loca_cd) {
        this.srl_loca_cd = srl_loca_cd;
    }

    public String getVend_no() {
        return vend_no;
    }

    public void setVend_no(String vend_no) {
        this.vend_no = vend_no;
    }

    public String getPrd_cd() {
        return prd_cd;
    }

    public void setPrd_cd(String prd_cd) {
        this.prd_cd = prd_cd;
    }

    public String getPrd_nm() {
        return prd_nm;
    }

    public void setPrd_nm(String prd_nm) {
        this.prd_nm = prd_nm;
    }

    @Override
    public String toString() {
        return "SED03F107ROutVo [count=" + count + ", sno=" + sno + ", whous_cd=" + whous_cd + ", rack_no=" + rack_no
                + ", icc_id=" + icc_id + ", sim_no=" + sim_no + ", srl_stat_cd=" + srl_stat_cd + ", srl_st_cd="
                + srl_st_cd + ", srl_loca_cd=" + srl_loca_cd + ", vend_no=" + vend_no + ", prd_cd=" + prd_cd
                + ", prd_nm=" + prd_nm + "]";
    }

}
