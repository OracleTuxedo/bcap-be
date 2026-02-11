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
public class RefreshTokenInDto {
    @NotBlank(message = "Refresh token is required")
    @Size(max = 36, message = "refreshToken must be at most 36 characters")
    private String refreshToken;
}
