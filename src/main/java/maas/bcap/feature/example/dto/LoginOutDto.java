package maas.bcap.feature.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginOutDto {

    private String usr_ctgo_cd;

    private String adm_usr_yn;
}
