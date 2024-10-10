package maas.bcap.module.ed.ed03.sed03f120r;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maas.bcap.module.ed.ed03.sed03f081r.SED03F081ROutSub1Vo;
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
public class SED03F120ROutVo {

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String wk_req_no;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String vend_no;

    @FIELD(kind = Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mid;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String wk_tp_cd;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tier_cd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String sla_cd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pfr_rank_cd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String wk_req_stat_cd;

    @FIELD(kind = Kind.DATA, length = 14, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String recp_dttm;

    @FIELD(kind = Kind.DATA, length = 14, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String goal_dttm;

    @FIELD(kind = Kind.DATA, length = 14, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String cmpl_dttm;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.DECIMAL, decimal = 2, sign_length = 1, point_length = 1)
    public double fee;

    @FIELD(kind = Kind.DATA, length = 1000, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String wk_req_expl;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String iosd_no;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String whous_cd;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_req_date;

    @FIELD(kind = Kind.DATA, length = 13, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_req_scrn_id;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_req_grup_no;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_req_seq_no;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_stat_cd;

    @FIELD(kind = Kind.DATA, length = 13, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_auth_chg_emp_no;

    @FIELD(kind = Kind.DATA, length = 14, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_dttm;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String apvl_req_keyctnts1;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_nm;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String area_cd;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String rgn_cd;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mb_clcd;

    @FIELD(kind = Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_tp_cd;

    @FIELD(kind = Kind.DATA, length = 60, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mero_nm;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_hp_tel_no;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_tel_no;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_fax_tel_no;

    @FIELD(kind = Kind.DATA, length = 50, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_email_addr;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_post;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_city_cd;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_dtl_addr1;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_dtl_addr2;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mer_dtl_addr3;

    @FIELD(kind = Kind.DATA, length = 60, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String srfnm;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String srtel_no;

    @FIELD(kind = Kind.DATA, length = 13, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String wk_istt_no;

    @FIELD(kind = Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mb_mid;

    @FIELD(kind = Kind.DATA, length = 1000, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String mid_member_bank;
}
