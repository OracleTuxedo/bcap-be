package maas.bcap.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDownloadInDto {
    private String fileDiv;

    private String attachFileId;

    private String attachFileSeqNo;

    private String chkFlag;

    private String screenId;
}
