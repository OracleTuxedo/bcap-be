package maas.bcap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoFileManagerDto {
    // private String userId; /// Harusnya didapatkan melalui Token (AuthInfoDto)
    private String screenId;
    private String userIp;
}
