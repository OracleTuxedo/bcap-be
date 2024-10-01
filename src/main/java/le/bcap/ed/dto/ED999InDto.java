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

    private String prd_tp_cd;

    private String sno;

    private String srl_stat_cd;

    private String srl_st_cd;

    private String prd_cd;

    private String icc_id;
}
