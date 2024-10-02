package mti.com.telegram.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUserDataOutput<T> {
    TelegramHeader header;

    TelegramMessage message;

    T output;

    List<T> outputList;

}
