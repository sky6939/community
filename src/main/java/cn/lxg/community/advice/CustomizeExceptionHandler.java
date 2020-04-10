package cn.lxg.community.advice;

import cn.lxg.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex,Model model) {
        if(ex instanceof CustomizeException) {
            model.addAttribute("error",ex.getMessage());
        } else {
            model.addAttribute("error","完蛋了完蛋了，服务器冒烟了，请稍后在访问！");
        }
        return new ModelAndView("error");
    }

}
