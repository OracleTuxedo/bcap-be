package mti.com.telegram.vo;

import java.util.List;

public class TelegramOutputUserData {
	Object output;

	List<Object> outputList;

	TelegramHeader header;

	TelegramMessage message;

	public Object getOutput() {
		return this.output;
	}

	public void setOutput(Object paramObject) {
		this.output = paramObject;
	}

	public TelegramMessage getMessage() {
		return this.message;
	}

	public void setMessage(TelegramMessage paramTelegramMessage) {
		this.message = paramTelegramMessage;
	}

	public List<Object> getOutputList() {
		return this.outputList;
	}

	public void setOutputList(List<Object> paramList) {
		this.outputList = paramList;
	}

	public TelegramHeader getHeader() {
		return this.header;
	}

	public void setHeader(TelegramHeader paramTelegramHeader) {
		this.header = paramTelegramHeader;
	}

	public String toString() {
		return "TelegramOutputUserData [output=" + this.output + ", outputList=" + this.outputList + ", message="
				+ this.message + "]";
	}
}
