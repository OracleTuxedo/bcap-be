package maas.bcap.module;

import javax.servlet.http.HttpServletRequest;

import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramUserDataOutput;

public interface BaseModuleInterface<T, V> {
    TelegramUserDataOutput<T> call(HttpServletRequest request, V inVo, String screenId) throws TelegramNestedRuntimeException, Exception;
}
