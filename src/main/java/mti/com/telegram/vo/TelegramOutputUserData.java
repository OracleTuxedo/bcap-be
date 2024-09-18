package mti.com.telegram.vo;

import java.util.List;

public class TelegramOutputUserData {
    Object output;
    List<Object> outputList;
    TelegramHeader header;
    TelegramMessage message;

    public TelegramOutputUserData() {
    }

    public Object getOutput() {
        return this.output;
    }

    public void setOutput(Object var1) {
        this.output = var1;
    }

    public TelegramMessage getMessage() {
        return this.message;
    }

    public void setMessage(TelegramMessage var1) {
        this.message = var1;
    }

    public List<Object> getOutputList() {
        return this.outputList;
    }

    public void setOutputList(List<Object> var1) {
        this.outputList = var1;
    }

    public TelegramHeader getHeader() {
        return this.header;
    }

    public void setHeader(TelegramHeader var1) {
        this.header = var1;
    }

    public String toString() {
        return "TelegramOutputUserData [output=" + this.output + ", outputList=" + this.outputList + ", message=" + this.message + "]";
    }
}
