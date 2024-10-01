package le.bcap.ed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ED999InDto {

    //SED03F107R
    private String prd_tp_cd;

    private String sno;

    private String srl_stat_cd;

    private String srl_st_cd;

    private String prd_cd;

    private String icc_id;

    // SAC02F452R
    private long page_no;

    private long page_size;

    private String mid;

    private String auth_strt_date;

    private String auth_end_date;
}
