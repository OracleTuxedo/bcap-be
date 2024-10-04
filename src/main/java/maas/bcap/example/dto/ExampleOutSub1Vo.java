package maas.bcap.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleOutSub1Vo {
    
    private String auth_date;

    private String pmt_date;

    private String card_no;

    private String auth_no;

    private long sale_amt;

    private long pwcw_csh_amt;

    private String dcctrans_yn;
}
