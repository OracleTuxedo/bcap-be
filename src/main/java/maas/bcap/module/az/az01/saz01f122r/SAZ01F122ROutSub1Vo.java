package maas.bcap.module.az.az01.saz01f122r;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SAZ01F122ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String parallel_id;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tx_date;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long exe_no;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String exe_desc;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String status;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long weight;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long schedule_type;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long schedule_count;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long send_key_type;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long total_data_count;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long success_count;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long error_count;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String first_key;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String last_key;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String send_start_key;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String send_end_key;
}
