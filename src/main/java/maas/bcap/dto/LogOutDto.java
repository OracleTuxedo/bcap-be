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
public class LogOutDto {

    @NotBlank(message = "User ID is required")
    @Size(max = 20, message = "userId must be at most 20 characters")
    private String userId;

    @NotBlank(message = "Screen ID is required")
    @Size(max = 50, message = "screenId must be at most 50 characters")
    private String screenId;

    @Size(max = 36, message = "refreshToken must be at most 36 characters")
    private String refreshToken;
}
