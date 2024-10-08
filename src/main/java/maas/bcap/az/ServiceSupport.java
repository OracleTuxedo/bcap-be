package maas.bcap.az;

import jakarta.servlet.http.HttpServletRequest;
import mti.com.system.SessionManager;
import mti.com.system.SessionVo;
import mti.com.telegram.util.InterfaceTelegram;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import java.util.Locale;

/**
 * Utility class to communicate with Telegram Layer.
 */
@Service
public class ServiceSupport {

    private static final Logger logger = LogManager.getLogger(ServiceSupport.class);

    /**
     * Create TelegramUserDataInput
     *
     * <p>
     * Included User, Locale, Client that being requested.
     * </p>
     *
     * @param request    provided by Spring framework
     * @param tuxedoCode name of online module inside DevonC
     * @param screenId   the name or ID of the page making the request
     * @return TelegramUserDataInput
     * @throws Exception exception
     * @see mti.com.telegram.vo.TelegramUserDataInput
     */
    public TelegramUserDataInput tuxedoHeader(HttpServletRequest request, String tuxedoCode, String screenId)
        throws Exception {

        SessionVo userVO = SessionManager.getUserData(request);

        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code(tuxedoCode);
        userData.setClient_ip_no(WebUtil.getClientIp(request));
        userData.setScrn_id(screenId);
        userData.setOp_id(userVO == null ? "undefined" : userVO.getSUserId());
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type(getLocaleCd(request));

        logger.info(userData.toString());

        return userData;
    }

    // Weblogic Connection
    public <T, V> TelegramUserDataOutput<T> tuxedoTransaction(TelegramUserDataInput userDataInput, V in, T out)
        throws Exception {
        TelegramUserDataOutput<T> result = InterfaceTelegram.interfaceTuxedo(userDataInput, in, out);
        if (result != null) {
            logger.info(result.toString());
        }
        return result;
    }

    public String getLocaleCd(HttpServletRequest request) {
        String lang = "EN";
        Locale locale = (Locale) WebUtils.getSessionAttribute(request,
            SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if (locale != null) {
//			lang = (locale.toString().toUpperCase()).equals("EN") ? "EN" : "ID";
            lang = (locale.toString()).equalsIgnoreCase("EN") ? "EN" : "ID";
        }

        logger.info(lang);

        return lang;
    }
}
