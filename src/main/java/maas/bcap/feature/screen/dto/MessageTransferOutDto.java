package maas.bcap.feature.screen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageTransferOutDto {
    private String encryptedMessages; // String Panjang

    private String iv;
}
