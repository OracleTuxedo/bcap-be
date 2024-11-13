package maas.bcap.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecryptionInDto {
    // Getter and Setter for encryptedData
    private String encryptedData;
    // Getter and Setter for iv
    private String iv;
}