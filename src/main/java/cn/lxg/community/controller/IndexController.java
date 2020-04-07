package cn.lxg.community.controller;
import	java.net.Authenticator;

import cn.lxg.community.mapper.UserMapper;
import cn.lxg.community.model.User;
import cn.lxg.community.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        String token = CookieUtil.getCookie(request.getCookies(), "token");
        if(token != null) {
            User user = userMapper.selectOneByToken(token);
            if(user != null) {
                request.getSession().setAttribute("user",user);
            }
        }
        return "index";
    }

}
