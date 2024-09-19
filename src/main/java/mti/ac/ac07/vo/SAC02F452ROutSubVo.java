package mti.ac.ac07.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

public class SAC02F452ROutSubVo {
    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_date;

    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_date;

    @FIELD(kind=Kind.DATA, length = 19, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String card_no;

    @FIELD(kind=Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_no;

    @FIELD(kind=Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0 ,sign_length=0 ,point_length=1)
    public long sale_amt;

    @FIELD(kind=Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0 ,sign_length=0 ,point_length=1)
    public long nonfareamt;

    @FIELD(kind=Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String sale_knd_clcd;

    @FIELD(kind=Kind.DATA, length = 5, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0)
    public long ins_mcnt;

    @FIELD(kind=Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0 ,sign_length=0 ,point_length=1)
    public long sale_pamt;

    @FIELD(kind=Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0 ,sign_length=0 ,point_length=1)
    public long sale_svc_fee;

    @FIELD(kind=Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0 ,sign_length=0 ,point_length=1)
    public long sale_tax;

    @FIELD(kind=Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0 ,sign_length=0 ,point_length=1)
    public long pwcw_csh_amt;

    @FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dcctrans_yn;



    public String getAuth_date() {
        return auth_date;
    }



    public void setAuth_date(String auth_date) {
        this.auth_date = auth_date;
    }



    public String getPmt_date() {
        return pmt_date;
    }



    public void setPmt_date(String pmt_date) {
        this.pmt_date = pmt_date;
    }



    public String getCard_no() {
        return card_no;
    }



    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }



    public String getAuth_no() {
        return auth_no;
    }



    public void setAuth_no(String auth_no) {
        this.auth_no = auth_no;
    }



    public long getSale_amt() {
        return sale_amt;
    }



    public void setSale_amt(long sale_amt) {
        this.sale_amt = sale_amt;
    }



    public long getNonfareamt() {
        return nonfareamt;
    }



    public void setNonfareamt(long nonfareamt) {
        this.nonfareamt = nonfareamt;
    }



    public String getSale_knd_clcd() {
        return sale_knd_clcd;
    }



    public void setSale_knd_clcd(String sale_knd_clcd) {
        this.sale_knd_clcd = sale_knd_clcd;
    }



    public long getIns_mcnt() {
        return ins_mcnt;
    }



    public void setIns_mcnt(long ins_mcnt) {
        this.ins_mcnt = ins_mcnt;
    }



    public long getSale_pamt() {
        return sale_pamt;
    }



    public void setSale_pamt(long sale_pamt) {
        this.sale_pamt = sale_pamt;
    }



    public long getSale_svc_fee() {
        return sale_svc_fee;
    }



    public void setSale_svc_fee(long sale_svc_fee) {
        this.sale_svc_fee = sale_svc_fee;
    }



    public long getSale_tax() {
        return sale_tax;
    }



    public void setSale_tax(long sale_tax) {
        this.sale_tax = sale_tax;
    }



    public long getPwcw_csh_amt() {
        return pwcw_csh_amt;
    }



    public void setPwcw_csh_amt(long pwcw_csh_amt) {
        this.pwcw_csh_amt = pwcw_csh_amt;
    }



    public String getDcctrans_yn() {
        return dcctrans_yn;
    }



    public void setDcctrans_yn(String dcctrans_yn) {
        this.dcctrans_yn = dcctrans_yn;
    }



    @Override
    public String toString() {
        return "SAC02F452ROutSubVo [auth_date=" + auth_date + ", pmt_date="
                + pmt_date + ", card_no=" + card_no + ", auth_no=" + auth_no
                + ", sale_amt=" + sale_amt + ", nonfareamt=" + nonfareamt
                + ", sale_knd_clcd=" + sale_knd_clcd + ", ins_mcnt=" + ins_mcnt
                + ", sale_pamt=" + sale_pamt + ", sale_svc_fee=" + sale_svc_fee
                + ", sale_tax=" + sale_tax + ", pwcw_csh_amt=" + pwcw_csh_amt
                + ", dcctrans_yn=" + dcctrans_yn + "]";
    }



}
