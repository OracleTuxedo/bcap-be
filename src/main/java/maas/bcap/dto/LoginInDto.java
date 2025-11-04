package maas.bcap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginInDto {
    private String userId;

    private String encryptionPassword;

    private String appType; // I = MTI, B = MBCS, M = MMP

    private String screenId;
}
