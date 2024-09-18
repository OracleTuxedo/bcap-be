package mti.com.telegram.vo;

public class TelegramInputUserData {
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

    public TelegramInputUserData() {
    }

    public String getTx_code() {
        return this.tx_code;
    }

    public void setTx_code(String var1) {
        this.tx_code = var1;
    }

    public String getOp_id() {
        return this.op_id;
    }

    public void setOp_id(String var1) {
        this.op_id = var1;
    }

    public String getAsync_rspn_yn() {
        return this.async_rspn_yn;
    }

    public void setAsync_rspn_yn(String var1) {
        this.async_rspn_yn = var1;
    }

    public String getRspn_svc_code() {
        return this.rspn_svc_code;
    }

    public void setRspn_svc_code(String var1) {
        this.rspn_svc_code = var1;
    }

    public String getClient_ip_no() {
        return this.client_ip_no;
    }

    public void setClient_ip_no(String var1) {
        this.client_ip_no = var1;
    }

    public String getClient_mac() {
        return this.client_mac;
    }

    public void setClient_mac(String var1) {
        this.client_mac = var1;
    }

    public String getScrn_id() {
        return this.scrn_id;
    }

    public void setScrn_id(String var1) {
        this.scrn_id = var1;
    }

    public String getSync_type() {
        return this.sync_type;
    }

    public void setSync_type(String var1) {
        this.sync_type = var1;
    }

    public int getTtl_use_flag() {
        return this.ttl_use_flag;
    }

    public void setTtl_use_flag(int var1) {
        this.ttl_use_flag = var1;
    }

    public int getTtl() {
        return this.ttl;
    }

    public void setTtl(int var1) {
        this.ttl = var1;
    }

    public int getLong_msg_type() {
        return this.long_msg_type;
    }

    public void setLong_msg_type(int var1) {
        this.long_msg_type = var1;
    }

    public String getDst_inst_code() {
        return this.dst_inst_code;
    }

    public void setDst_inst_code(String var1) {
        this.dst_inst_code = var1;
    }

    public String getFail_knd() {
        return this.fail_knd;
    }

    public void setFail_knd(String var1) {
        this.fail_knd = var1;
    }

    public String getAp_host_name() {
        return this.ap_host_name;
    }

    public void setAp_host_name(String var1) {
        this.ap_host_name = var1;
    }

    public String getAp_caller_id() {
        return this.ap_caller_id;
    }

    public void setAp_caller_id(String var1) {
        this.ap_caller_id = var1;
    }

    public String getLang_type() {
        return this.lang_type;
    }

    public void setLang_type(String var1) {
        this.lang_type = var1;
    }
}
