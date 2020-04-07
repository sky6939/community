package cn.lxg.community.utils;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public static String getCookie( Cookie[] cookies, String cookieName) {
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
