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

	public String getTx_code() {
		return this.tx_code;
	}

	public void setTx_code(String paramString) {
		this.tx_code = paramString;
	}

	public String getOp_id() {
		return this.op_id;
	}

	public void setOp_id(String paramString) {
		this.op_id = paramString;
	}

	public String getAsync_rspn_yn() {
		return this.async_rspn_yn;
	}

	public void setAsync_rspn_yn(String paramString) {
		this.async_rspn_yn = paramString;
	}

	public String getRspn_svc_code() {
		return this.rspn_svc_code;
	}

	public void setRspn_svc_code(String paramString) {
		this.rspn_svc_code = paramString;
	}

	public String getClient_ip_no() {
		return this.client_ip_no;
	}

	public void setClient_ip_no(String paramString) {
		this.client_ip_no = paramString;
	}

	public String getClient_mac() {
		return this.client_mac;
	}

	public void setClient_mac(String paramString) {
		this.client_mac = paramString;
	}

	public String getScrn_id() {
		return this.scrn_id;
	}

	public void setScrn_id(String paramString) {
		this.scrn_id = paramString;
	}

	public String getSync_type() {
		return this.sync_type;
	}

	public void setSync_type(String paramString) {
		this.sync_type = paramString;
	}

	public int getTtl_use_flag() {
		return this.ttl_use_flag;
	}

	public void setTtl_use_flag(int paramInt) {
		this.ttl_use_flag = paramInt;
	}

	public int getTtl() {
		return this.ttl;
	}

	public void setTtl(int paramInt) {
		this.ttl = paramInt;
	}

	public int getLong_msg_type() {
		return this.long_msg_type;
	}

	public void setLong_msg_type(int paramInt) {
		this.long_msg_type = paramInt;
	}

	public String getDst_inst_code() {
		return this.dst_inst_code;
	}

	public void setDst_inst_code(String paramString) {
		this.dst_inst_code = paramString;
	}

	public String getFail_knd() {
		return this.fail_knd;
	}

	public void setFail_knd(String paramString) {
		this.fail_knd = paramString;
	}

	public String getAp_host_name() {
		return this.ap_host_name;
	}

	public void setAp_host_name(String paramString) {
		this.ap_host_name = paramString;
	}

	public String getAp_caller_id() {
		return this.ap_caller_id;
	}

	public void setAp_caller_id(String paramString) {
		this.ap_caller_id = paramString;
	}

	public String getLang_type() {
		return this.lang_type;
	}

	public void setLang_type(String paramString) {
		this.lang_type = paramString;
	}
}
