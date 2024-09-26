package le.bcap.ac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SAC02F452ROutSub1Dto {
    private String auth_date;

    private String payment_date;

    private String card_no;

    private String auth_no;

    private long sale_amount;

    private long non_fare_amount;

    private String sale_kind_classify_code;

    private long installment_month_code;

    private long sale_principal_amount;

    private long sale_service_fee;

    private long sale_tax;

    private long pwcw_cash_amount;

    private String dcc_transaction_yes_or_no;
}
