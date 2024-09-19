package mti.ac.ac07.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

public class SAC02F452RInVo {

    @FIELD(kind=Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0)
    public long page_no;

    @FIELD(kind=Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0)
    public long page_size;

    @FIELD(kind=Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mid;

    @FIELD(kind=Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_no;

    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_strt_date;

    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_end_date;

    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_strt_date;

    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_end_date;


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAuth_no() {
        return auth_no;
    }

    public void setAuth_no(String auth_no) {
        this.auth_no = auth_no;
    }

    public String getAuth_strt_date() {
        return auth_strt_date;
    }

    public void setAuth_strt_date(String auth_strt_date) {
        this.auth_strt_date = auth_strt_date;
    }

    public String getAuth_end_date() {
        return auth_end_date;
    }

    public void setAuth_end_date(String auth_end_date) {
        this.auth_end_date = auth_end_date;
    }

    public String getPmt_strt_date() {
        return pmt_strt_date;
    }

    public void setPmt_strt_date(String pmt_strt_date) {
        this.pmt_strt_date = pmt_strt_date;
    }

    public String getPmt_end_date() {
        return pmt_end_date;
    }

    public void setPmt_end_date(String pmt_end_date) {
        this.pmt_end_date = pmt_end_date;
    }

    public long getPage_no() {
        return page_no;
    }

    public void setPage_no(long page_no) {
        this.page_no = page_no;
    }


    public long getPage_size() {
        return page_size;
    }

    public void setPage_size(long page_size) {
        this.page_size = page_size;
    }

    @Override
    public String toString() {
        return "MC07V003RInVo [mid=" + mid
                + ", auth_no=" + auth_no
                + ", auth_strt_date=" + auth_strt_date
                + ", auth_end_date=" + auth_end_date
                + ", pmt_strt_date=" + pmt_strt_date
                + ", pmt_end_date=" + pmt_end_date
                + ", page_no=" + page_no
                + "]";
    }
}
