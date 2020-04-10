package cn.lxg.community.controller;

import cn.lxg.community.dto.PageDTO;
import cn.lxg.community.model.User;
import cn.lxg.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{active}")
    public String profile(@PathVariable(name = "active")String active,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "currPage", defaultValue = "1")Integer currPage,
                          @RequestParam(name = "size",defaultValue = "5")Integer size) {
        model.addAttribute("active",active);
        //获得用户
        User user = (User)request.getSession().getAttribute("user");
        switch(active) {
            case "questions":
                model.addAttribute("activeName","我的问题");
                PageDTO questions = questionService.findAllById(user.getId(), currPage, size);
                model.addAttribute("page",questions);
                break;
            case "reply":
                model.addAttribute("activeName","最新回复");
                break;
        }

        return "profile";
    }

}
