package mti.ac.ac04.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SAC04V224UInSub1Vo {

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String req_date;

    @FIELD(kind = Kind.DATA, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String req_seq_no;

    @FIELD(kind = Kind.DATA, length = 50, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String email_addr;

}
