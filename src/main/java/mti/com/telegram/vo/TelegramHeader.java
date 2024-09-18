package mti.com.telegram.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramHeader {
	@FIELD(kind = Kind.HEADER, length = 8, type = FieldType.NUMBER, trim = TrimType.LTRIM)
	@DATATYPE(type = NumberType.INT, decimal = 0)
	public int msg_len;

	@FIELD(kind = Kind.HEADER, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String gid_sysname;

	@FIELD(kind = Kind.HEADER, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String gid_yyyyymmdd;

	@FIELD(kind = Kind.HEADER, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String gid_hhmmss;

	@FIELD(kind = Kind.HEADER, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String gid_seq;

	@FIELD(kind = Kind.HEADER, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String gid_pid;

	@FIELD(kind = Kind.HEADER, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String gid_stat;

	@FIELD(kind = Kind.HEADER, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String tx_code;

	@FIELD(kind = Kind.HEADER, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String inst_no;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String send_rspn_type;

	@FIELD(kind = Kind.HEADER, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String rspn_svc_code;

	@FIELD(kind = Kind.HEADER, length = 32, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String ori_global_id;

	@FIELD(kind = Kind.HEADER, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String ori_send_time;

	@FIELD(kind = Kind.HEADER, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String chnl_id;

	@FIELD(kind = Kind.HEADER, length = 32, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String client_ip_no;

	@FIELD(kind = Kind.HEADER, length = 12, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String client_mac;

	@FIELD(kind = Kind.HEADER, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String scrn_id;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String scrn_lock_yn;

	@FIELD(kind = Kind.HEADER, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String op_id;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
	@DATATYPE(type = NumberType.INT, decimal = 0)
	public int xa_begin_flag;

	@FIELD(kind = Kind.HEADER, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String send_time;

	@FIELD(kind = Kind.HEADER, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String rspn_time;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String sync_type;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String async_rspn_yn;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
	@DATATYPE(type = NumberType.INT, decimal = 0)
	public int call_depth;

	@FIELD(kind = Kind.HEADER, length = 3, type = FieldType.NUMBER, trim = TrimType.LTRIM)
	@DATATYPE(type = NumberType.INT, decimal = 0)
	public int msg_count_no;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
	@DATATYPE(type = NumberType.INT, decimal = 0)
	public int ttl_use_flag;

	@FIELD(kind = Kind.HEADER, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String ttl_from_time;

	@FIELD(kind = Kind.HEADER, length = 3, type = FieldType.NUMBER, trim = TrimType.LTRIM)
	@DATATYPE(type = NumberType.INT, decimal = 0)
	public int ttl;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
	@DATATYPE(type = NumberType.INT, decimal = 0)
	public int long_msg_type;

	@FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
	@DATATYPE(type = NumberType.INT, decimal = 0)
	public int err_flag;

	@FIELD(kind = Kind.HEADER, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String err_src;

	@FIELD(kind = Kind.HEADER, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String err_type;

	@FIELD(kind = Kind.HEADER, length = 9, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String err_code;

	@FIELD(kind = Kind.HEADER, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String dst_inst_code;

	@FIELD(kind = Kind.HEADER, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String fail_knd;

	@FIELD(kind = Kind.HEADER, length = 16, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String ap_host_name;

	@FIELD(kind = Kind.HEADER, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String ap_caller_id;

	@FIELD(kind = Kind.HEADER, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String inf_id;

	@FIELD(kind = Kind.HEADER, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String lang_type;

	@FIELD(kind = Kind.HEADER, length = 141, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String reserved;

	public int getMsg_len() {
		return this.msg_len;
	}

	public void setMsg_len(int paramInt) {
		this.msg_len = paramInt;
	}

	public String getGid_sysname() {
		return this.gid_sysname;
	}

	public void setGid_sysname(String paramString) {
		this.gid_sysname = paramString;
	}

	public String getGid_yyyyymmdd() {
		return this.gid_yyyyymmdd;
	}

	public void setGid_yyyyymmdd(String paramString) {
		this.gid_yyyyymmdd = paramString;
	}

	public String getGid_hhmmss() {
		return this.gid_hhmmss;
	}

	public void setGid_hhmmss(String paramString) {
		this.gid_hhmmss = paramString;
	}

	public String getGid_seq() {
		return this.gid_seq;
	}

	public void setGid_seq(String paramString) {
		this.gid_seq = paramString;
	}

	public String getGid_pid() {
		return this.gid_pid;
	}

	public void setGid_pid(String paramString) {
		this.gid_pid = paramString;
	}

	public String getGid_stat() {
		return this.gid_stat;
	}

	public void setGid_stat(String paramString) {
		this.gid_stat = paramString;
	}

	public String getTx_code() {
		return this.tx_code;
	}

	public void setTx_code(String paramString) {
		this.tx_code = paramString;
	}

	public String getInst_no() {
		return this.inst_no;
	}

	public void setInst_no(String paramString) {
		this.inst_no = paramString;
	}

	public String getSend_rspn_type() {
		return this.send_rspn_type;
	}

	public void setSend_rspn_type(String paramString) {
		this.send_rspn_type = paramString;
	}

	public String getRspn_svc_code() {
		return this.rspn_svc_code;
	}

	public void setRspn_svc_code(String paramString) {
		this.rspn_svc_code = paramString;
	}

	public String getOri_global_id() {
		return this.ori_global_id;
	}

	public void setOri_global_id(String paramString) {
		this.ori_global_id = paramString;
	}

	public String getOri_send_time() {
		return this.ori_send_time;
	}

	public void setOri_send_time(String paramString) {
		this.ori_send_time = paramString;
	}

	public String getChnl_id() {
		return this.chnl_id;
	}

	public void setChnl_id(String paramString) {
		this.chnl_id = paramString;
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

	public String getScrn_lock_yn() {
		return this.scrn_lock_yn;
	}

	public void setScrn_lock_yn(String paramString) {
		this.scrn_lock_yn = paramString;
	}

	public String getOp_id() {
		return this.op_id;
	}

	public void setOp_id(String paramString) {
		this.op_id = paramString;
	}

	public int getXa_begin_flag() {
		return this.xa_begin_flag;
	}

	public void setXa_begin_flag(int paramInt) {
		this.xa_begin_flag = paramInt;
	}

	public String getSend_time() {
		return this.send_time;
	}

	public void setSend_time(String paramString) {
		this.send_time = paramString;
	}

	public String getRspn_time() {
		return this.rspn_time;
	}

	public void setRspn_time(String paramString) {
		this.rspn_time = paramString;
	}

	public String getSync_type() {
		return this.sync_type;
	}

	public void setSync_type(String paramString) {
		this.sync_type = paramString;
	}

	public String getAsync_rspn_yn() {
		return this.async_rspn_yn;
	}

	public void setAsync_rspn_yn(String paramString) {
		this.async_rspn_yn = paramString;
	}

	public int getCall_depth() {
		return this.call_depth;
	}

	public void setCall_depth(int paramInt) {
		this.call_depth = paramInt;
	}

	public int getMsg_count_no() {
		return this.msg_count_no;
	}

	public void setMsg_count_no(int paramInt) {
		this.msg_count_no = paramInt;
	}

	public int getTtl_use_flag() {
		return this.ttl_use_flag;
	}

	public void setTtl_use_flag(int paramInt) {
		this.ttl_use_flag = paramInt;
	}

	public String getTtl_from_time() {
		return this.ttl_from_time;
	}

	public void setTtl_from_time(String paramString) {
		this.ttl_from_time = paramString;
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

	public int getErr_flag() {
		return this.err_flag;
	}

	public void setErr_flag(int paramInt) {
		this.err_flag = paramInt;
	}

	public String getErr_src() {
		return this.err_src;
	}

	public void setErr_src(String paramString) {
		this.err_src = paramString;
	}

	public String getErr_type() {
		return this.err_type;
	}

	public void setErr_type(String paramString) {
		this.err_type = paramString;
	}

	public String getErr_code() {
		return this.err_code;
	}

	public void setErr_code(String paramString) {
		this.err_code = paramString;
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

	public String getInf_id() {
		return this.inf_id;
	}

	public void setInf_id(String paramString) {
		this.inf_id = paramString;
	}

	public String getLang_type() {
		return this.lang_type;
	}

	public void setLang_type(String paramString) {
		this.lang_type = paramString;
	}

	public String getReserved() {
		return this.reserved;
	}

	public void setReserved(String paramString) {
		this.reserved = paramString;
	}

	public String toString() {
		return "TelegramHeader [msg_len=" + this.msg_len + ", gid_sysname=" + this.gid_sysname + ", gid_yyyyymmdd="
				+ this.gid_yyyyymmdd + ", gid_hhmmss=" + this.gid_hhmmss + ", gid_seq=" + this.gid_seq + ", gid_pid="
				+ this.gid_pid + ", gid_stat=" + this.gid_stat + ", tx_code=" + this.tx_code + ", inst_no="
				+ this.inst_no + ", send_rspn_type=" + this.send_rspn_type + ", rspn_svc_code=" + this.rspn_svc_code
				+ ", ori_global_id=" + this.ori_global_id + ", ori_send_time=" + this.ori_send_time + ", chnl_id="
				+ this.chnl_id + ", client_ip_no=" + this.client_ip_no + ", client_mac=" + this.client_mac
				+ ", scrn_id=" + this.scrn_id + ", scrn_lock_yn=" + this.scrn_lock_yn + ", op_id=" + this.op_id
				+ ", xa_begin_flag=" + this.xa_begin_flag + ", send_time=" + this.send_time + ", rspn_time="
				+ this.rspn_time + ", sync_type=" + this.sync_type + ", async_rspn_yn=" + this.async_rspn_yn
				+ ", call_depth=" + this.call_depth + ", msg_count_no=" + this.msg_count_no + ", ttl_use_flag="
				+ this.ttl_use_flag + ", ttl_from_time=" + this.ttl_from_time + ", ttl=" + this.ttl + ", long_msg_type="
				+ this.long_msg_type + ", err_flag=" + this.err_flag + ", err_src=" + this.err_src + ", err_type="
				+ this.err_type + ", err_code=" + this.err_code + ", dst_inst_code=" + this.dst_inst_code
				+ ", fail_knd=" + this.fail_knd + ", ap_host_name=" + this.ap_host_name + ", ap_caller_id="
				+ this.ap_caller_id + ", inf_id=" + this.inf_id + ", lang_type=" + this.lang_type + ", reserved="
				+ this.reserved + "]";
	}
}
