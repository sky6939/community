package cn.lxg.community.controller;
import cn.lxg.community.mapper.QuestionMapper;
import cn.lxg.community.mapper.UserMapper;
import cn.lxg.community.model.Question;
import cn.lxg.community.model.User;
import cn.lxg.community.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String question(Question question, HttpServletRequest request, Model model) {
        String token = CookieUtil.getCookie(request.getCookies(), "token");
        User user = userMapper.selectOneByToken(token);
        if(user == null) {
            model.addAttribute("error","用户未登录！");
            return "publish";
        }
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        questionMapper.save(question);
        return "redirect:/";
    }

}
