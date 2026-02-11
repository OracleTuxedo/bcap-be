package maas.bcap.dto;

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
public class FileDownloadInDto {
    @NotBlank(message = "fileDiv is required")
    @Size(max = 50, message = "fileDiv must be at most 50 characters")
    private String fileDiv;

    @Size(max = 50, message = "attachFileId must be at most 50 characters")
    private String attachFileId;

    @Size(max = 20, message = "attachFileSeqNo must be at most 20 characters")
    private String attachFileSeqNo;

    @Size(max = 10, message = "chkFlag must be at most 10 characters")
    private String chkFlag;

    @NotBlank(message = "screenId is required")
    @Size(max = 50, message = "screenId must be at most 50 characters")
    private String screenId;
}
