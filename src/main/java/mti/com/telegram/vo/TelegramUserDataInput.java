package mti.com.telegram.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUserDataInput {
    public String tx_code;
    public String client_ip_no;
    public String client_mac;
    public String scrn_id;
    public String op_id;
    public String sync_type;
    public String async_rspn_yn;
    public String rspn_svc_code;
    public int ttl_use_flag = 0;
    public int ttl = 0;
    public int long_msg_type = 1;
    public String dst_inst_code;
    public String fail_knd;
    public String ap_host_name;
    public String ap_caller_id;
    public String lang_type = "EN";

}
