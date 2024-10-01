package le.bcap.az;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.HttpServletRequest;
import mti.com.system.SessionManager;
import mti.com.system.SessionVo;
import mti.com.telegram.util.InterfaceTelegram;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramOutputUserData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class ServiceSupport {

	private static final Logger logger = LogManager.getLogger(ServiceSupport.class);

	public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	public TelegramInputUserData tuxedoHeader(HttpServletRequest request, String tuxedoCode, String screenId)
			throws Exception {

		SessionVo userVO = SessionManager.getUserData(request);

		TelegramInputUserData userData = new TelegramInputUserData();
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

	public TelegramOutputUserData tuxedoTransaction(TelegramInputUserData userData, Object in, Object out)
			throws Exception {
		TelegramOutputUserData result = (TelegramOutputUserData) InterfaceTelegram.interfaceTuxedo(userData, in, out);
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
