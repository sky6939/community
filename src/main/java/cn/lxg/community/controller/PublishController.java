package cn.lxg.community.controller;
import cn.lxg.community.exception.CustomizeErrorCode;
import cn.lxg.community.exception.CustomizeException;
import cn.lxg.community.mapper.QuestionMapper;
import cn.lxg.community.mapper.UserMapper;
import cn.lxg.community.model.Question;
import cn.lxg.community.model.User;
import cn.lxg.community.service.QuestionService;
import cn.lxg.community.service.UserService;
import cn.lxg.community.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Integer id,
                        Model model) {
        Question question = questionMapper.findById(id);
        model.addAttribute("question",question);
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String question(Question question, HttpServletRequest request, Model model) {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOG_IN);
        }
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

}
