package cn.lxg.community.controller;

import cn.lxg.community.dto.QuestionDTO;
import cn.lxg.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model) {
        QuestionDTO question = questionService.findById(id);
        model.addAttribute("question",question);
        return "question";
    }


}
