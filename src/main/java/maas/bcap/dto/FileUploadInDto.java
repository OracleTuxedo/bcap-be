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
public class FileUploadInDto {
    private String fileDiv;

    private String fileDesc;

    private String extraPath;

    private String attachFileId;

    private List<String> attachFileSeqNo;

    // private String userId; /// Harusnya didapatkan melalui Token (AuthInfoDto)

    private String screenId;
}
