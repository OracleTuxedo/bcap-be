package maas.bcap.screen.fileupload.vo;
import java.util.List;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;
public class FileManagerOutVo {


    @FIELD(kind=Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String rson_cd;

    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String attach_file_id;

    @FIELD(kind=Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String attach_file_clcd;

    @FIELD(kind=Kind.DATA, length = 256, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String attach_file_expl;

    @FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String upd_yn;

    @FIELD(kind=Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String req_clcd;

    @FIELD(kind=Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<FileManagerOutSubVo> list;


    public String getRson_cd() {
        return rson_cd;
    }

    public void setRson_cd(String rson_cd) {
        this.rson_cd = rson_cd;
    }

    public String getAttach_file_expl() {
        return attach_file_expl;
    }

    public void setAttach_file_expl(String attach_file_expl) {
        this.attach_file_expl = attach_file_expl;
    }

    public String getAttach_file_id() {
        return attach_file_id;
    }

    public void setAttach_file_id(String attach_file_id) {
        this.attach_file_id = attach_file_id;
    }

    public String getAttach_file_clcd() {
        return attach_file_clcd;
    }

    public void setAttach_file_clcd(String attach_file_clcd) {
        this.attach_file_clcd = attach_file_clcd;
    }

    public String getUpd_yn() {
        return upd_yn;
    }

    public void setUpd_yn(String upd_yn) {
        this.upd_yn = upd_yn;
    }

    public String getReq_clcd() {
        return req_clcd;
    }


    public void setReq_clcd(String req_clcd) {
        this.req_clcd = req_clcd;
    }


    public List<FileManagerOutSubVo> getList() {
        return list;
    }


    @Override
    public String toString() {
        return "FileInfoOutVO [rson_cd=" + rson_cd + ", attach_file_id="
            + attach_file_id + ", attach_file_clcd=" + attach_file_clcd
            + ", attach_file_expl=" + attach_file_expl + ", upd_yn="
            + upd_yn + ", req_clcd=" + req_clcd + ", list=" + list + "]";
    }

    public void setList(List<FileManagerOutSubVo> list) {
        this.list = list;
    }

}
