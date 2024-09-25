package mti.com.system;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionManager {

    public static boolean isLoggedIn(HttpServletRequest request) {
        SessionVo user = getUserData(request);
        if (user == null || user.getSUserId() == null || user.getSUserId().length() == 0)
            return false;
        else
            return true;
    }

    public static SessionVo getUserData(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (SessionVo) session.getAttribute(SessionVo.SESSION_DATA_KEY);
    }

    public static void setUserData(HttpServletRequest req, SessionVo sessionData) {
        HttpSession session = req.getSession();
        session.setAttribute(SessionVo.SESSION_DATA_KEY, sessionData);
    }

    public static void alertSessionEnd(HttpServletRequest req, SessionVo sessionData) {
        HttpSession session = req.getSession();
        session.invalidate();
    }
}
