package maas.bcap.module.az.az01.saz01f123r;

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
public class SAZ01F123ROutSub1Vo {

    @FIELD(kind = Kind.DATA, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String parallel_id;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tx_date;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long exe_no;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tx_key;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String status;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long send_key_type;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long send_count;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String send_start_key;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String send_end_key;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String cur_proc_key;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long success_count;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long error_count;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long req_code;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long res_code;

    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long retry_count;

    @FIELD(kind = Kind.DATA, length = 64, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String svr_id;

    @FIELD(kind = Kind.DATA, length = 150, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String error_ctnt;
}
