package maas.bcap.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadInDto {
    @NotBlank(message = "fileDiv is required")
    @Size(max = 50, message = "fileDiv must be at most 50 characters")
    private String fileDiv;

    @Size(max = 200, message = "fileDesc must be at most 200 characters")
    private String fileDesc;

    @Size(max = 200, message = "extraPath must be at most 200 characters")
    private String extraPath;

    @Size(max = 50, message = "attachFileId must be at most 50 characters")
    private String attachFileId;

    private List<String> attachFileSeqNo;

    // private String userId; /// Harusnya didapatkan melalui Token (AuthInfoDto)

    @NotBlank(message = "screenId is required")
    @Size(max = 50, message = "screenId must be at most 50 characters")
    private String screenId;
}
