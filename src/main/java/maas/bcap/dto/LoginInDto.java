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
public class LoginInDto {
    @NotBlank(message = "userId is required")
    @Size(max = 20)
    private String userId;

    @NotBlank(message = "password is required")
    private String encryptionPassword;

    @NotBlank(message = "appType is required")
    @Size(max = 1)
    private String appType; // I = MTI, B = MBCS, M = MMP

    @NotBlank(message = "screenId is required")
    private String screenId;
}
