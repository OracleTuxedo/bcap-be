package maas.bcap.dto;

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
public class FileOutSub1Vo {
    @FIELD(kind=Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String attach_file_id;

	@FIELD(kind=Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String attach_file_seq_no;

	@FIELD(kind=Kind.DATA, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String attach_file_clcd;

	@FIELD(kind=Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String del_yn;

	@FIELD(kind=Kind.DATA, length = 200, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String file_nm;

	@FIELD(kind=Kind.DATA, length = 20, type = FieldType.NUMBER, trim = TrimType.RTRIM)
	@DATATYPE(type=NumberType.DECIMAL, decimal=0, point_length=1)
	public long upl_file_size;

	@FIELD(kind=Kind.DATA, length = 220, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String upl_file_nm;

	@FIELD(kind=Kind.DATA, length = 500, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String file_path;

	@FIELD(kind=Kind.DATA, length = 5, type = FieldType.NUMBER, trim = TrimType.RTRIM)
	@DATATYPE(type=NumberType.LONG, decimal=0)
	public long pfr_rank;

	@FIELD(kind=Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String inp_usr_id;

	@FIELD(kind=Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String inp_pgm_id;

	@FIELD(kind=Kind.DATA, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String chng_usr_id;

	@FIELD(kind=Kind.DATA, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
	public String chng_pgm_id;
}
