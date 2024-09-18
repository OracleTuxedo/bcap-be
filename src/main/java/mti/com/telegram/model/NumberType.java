package mti.com.telegram.model;

public enum NumberType {
    DECIMAL("Decimal"),
    DOUBLE("Double"),
    FLOAT("Float"),
    INT("Integer"),
    LONG("Long"),
    SHORT("Short");

    String numberType;

    private NumberType(String var3) {
        this.numberType = var3;
    }

    public String getNumberType() {
        return this.numberType;
    }
}
