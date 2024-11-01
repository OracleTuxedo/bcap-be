package maas.bcap.feature.screen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageTransferInDto {
    private String encryptedMessage;

    private String iv;

}