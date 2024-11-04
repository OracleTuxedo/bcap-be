package mti.com.system;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CookieManager {

    public static boolean isLoggedIn(HttpServletRequest request) {
        CookieVo user = getUserData(request);
        if (user == null || user.getSUserId() == null || user.getSUserId().length() == 0)
            return false;
        else
            return true;
    }

    public static CookieVo getUserData(HttpServletRequest request) {
        HttpSession cookie = request.getSession(false);
        if (cookie == null) {
            return null;
        }
        return (CookieVo) cookie.getAttribute(CookieVo.SESSION_DATA_KEY);
    }

    public static void setUserData(HttpServletRequest req, CookieVo cookieData) {
        HttpSession cookie = req.getSession();
        cookie.setAttribute(CookieVo.SESSION_DATA_KEY, cookieData);
    }

    public static void destroyUserData(HttpServletRequest req) {
        HttpSession Cookie = req.getSession();
        Cookie.invalidate();
    }
}
