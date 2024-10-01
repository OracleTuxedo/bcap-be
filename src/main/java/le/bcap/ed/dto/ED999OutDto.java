package le.bcap.ed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ED999OutDto {

    // SED03F107R
    private long count;

    private String sno;

    private String whous_cd;

    private String rack_no;

    private String icc_id;

    private String sim_no;

    private String srl_stat_cd;

    private String srl_st_cd;

    private String srl_loca_cd;

    private String vend_no;

    private String prd_cd;

    private String prd_nm;

    // SAC02F452R
    private long tot_cnt;

    private List<ED999OutSub1Vo> sub1Vos;
}
