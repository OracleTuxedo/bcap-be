package maas.bcap.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageTransferInDto {
    @NotBlank(message = "Encrypted message is required")
    private String encryptedMessage;

    @NotBlank(message = "IV is required")
    private String iv;

}