package maas.bcap.module;

import jakarta.servlet.http.HttpServletRequest;
import mti.com.telegram.vo.TelegramUserDataOutput;

public interface BaseModuleInterface<T, V> {
    TelegramUserDataOutput<T> call(HttpServletRequest request, V inVo, String screenId);
}
