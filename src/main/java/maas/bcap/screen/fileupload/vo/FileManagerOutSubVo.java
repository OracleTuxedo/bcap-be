package maas.bcap.screen.fileupload.vo;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
public class FileManagerOutSubVo {


    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String attach_file_id;

    @FIELD(kind=Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String attach_file_seq_no;

    @FIELD(kind=Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String attach_file_clcd;

    @FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String del_yn;

    @FIELD(kind=Kind.DATA, length = 200, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String file_nm;

    @FIELD(kind=Kind.DATA, length = 20, type = FieldType.NUMBER, trim = TrimType.RTRIM)
    @DATATYPE(type=NumberType.DECIMAL, decimal=0, point_length=1)
    public long upl_file_size;

    @FIELD(kind=Kind.DATA, length = 220, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String upl_file_nm;

    @FIELD(kind=Kind.DATA, length = 500, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String file_path;

    @FIELD(kind=Kind.DATA, length = 5, type = FieldType.NUMBER, trim = TrimType.RTRIM)
    @DATATYPE(type=NumberType.LONG, decimal=0)
    public long pfr_rank;

    @FIELD(kind=Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String inp_usr_id;

    @FIELD(kind=Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String inp_pgm_id;

    @FIELD(kind=Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String chng_usr_id;

    @FIELD(kind=Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String chng_pgm_id;

    public String getAttach_file_id() {
        return attach_file_id;
    }

    public void setAttach_file_id(String attach_file_id) {
        this.attach_file_id = attach_file_id;
    }

    public String getAttach_file_seq_no() {
        return attach_file_seq_no;
    }

    public void setAttach_file_seq_no(String attach_file_seq_no) {
        this.attach_file_seq_no = attach_file_seq_no;
    }

    public String getAttach_file_clcd() {
        return attach_file_clcd;
    }

    public void setAttach_file_clcd(String attach_file_clcd) {
        this.attach_file_clcd = attach_file_clcd;
    }

    public String getDel_yn() {
        return del_yn;
    }

    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
    }

    public String getFile_nm() {
        return file_nm;
    }

    public void setFile_nm(String file_nm) {
        this.file_nm = file_nm;
    }

    public long getUpl_file_size() {
        return upl_file_size;
    }

    public void setUpl_file_size(long upl_file_size) {
        this.upl_file_size = upl_file_size;
    }

    public String getUpl_file_nm() {
        return upl_file_nm;
    }

    public void setUpl_file_nm(String upl_file_nm) {
        this.upl_file_nm = upl_file_nm;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public long getPfr_rank() {
        return pfr_rank;
    }

    public void setPfr_rank(long pfr_rank) {
        this.pfr_rank = pfr_rank;
    }

    public String getInp_usr_id() {
        return inp_usr_id;
    }

    public void setInp_usr_id(String inp_usr_id) {
        this.inp_usr_id = inp_usr_id;
    }

    public String getInp_pgm_id() {
        return inp_pgm_id;
    }

    public void setInp_pgm_id(String inp_pgm_id) {
        this.inp_pgm_id = inp_pgm_id;
    }

    public String getChng_usr_id() {
        return chng_usr_id;
    }

    public void setChng_usr_id(String chng_usr_id) {
        this.chng_usr_id = chng_usr_id;
    }

    public String getChng_pgm_id() {
        return chng_pgm_id;
    }

    public void setChng_pgm_id(String chng_pgm_id) {
        this.chng_pgm_id = chng_pgm_id;
    }

    @Override
    public String toString() {
        return "FileInfoOutSubVO [attach_file_id=" + attach_file_id
            + ", attach_file_seq_no=" + attach_file_seq_no
            + ", attach_file_clcd=" + attach_file_clcd + ", del_yn="
            + del_yn + ", file_nm=" + file_nm + ", upl_file_size="
            + upl_file_size + ", upl_file_nm=" + upl_file_nm
            + ", file_path=" + file_path + ", pfr_rank=" + pfr_rank
            + ", inp_usr_id=" + inp_usr_id + ", inp_pgm_id=" + inp_pgm_id
            + ", chng_usr_id=" + chng_usr_id + ", chng_pgm_id="
            + chng_pgm_id + "]";
    }
}
