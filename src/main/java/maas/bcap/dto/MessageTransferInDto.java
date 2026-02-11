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
public class MessageTransferInDto {
    @NotBlank(message = "Encrypted message is required")
    @Size(max = 1_048_576, message = "encryptedMessage must be at most 1MB")
    private String encryptedMessage;

    @NotBlank(message = "IV is required")
    @Size(min = 24, max = 24, message = "iv must be exactly 24 characters")
    private String iv;

}