package cn.lxg.community.controller;

import cn.lxg.community.dto.PageDTO;
import cn.lxg.community.mapper.UserMapper;
import cn.lxg.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{msg}")
    public String error(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "currPage", defaultValue = "1")Integer currPage,
                        @RequestParam(name = "size",defaultValue = "5")Integer size
    ) {

        return "index";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "currPage", defaultValue = "1")Integer currPage,
                        @RequestParam(name = "size",defaultValue = "5")Integer size
                        ) {
        PageDTO pageDTO = questionService.findAll(currPage, size);
        model.addAttribute("page",pageDTO);
        return "index";
    }

}
