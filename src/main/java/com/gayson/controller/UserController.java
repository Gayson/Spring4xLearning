package com.gayson.controller;

import com.gayson.domain.User;
import com.gayson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by jixunzhen on 2017/4/5.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index.html")
    public String index(){
        return "login";
    }

    @RequestMapping(value = "/login.html")
    public ModelAndView login(HttpServletRequest request){
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        boolean isUserExist=userService.hasMatchUser(userName,password);
        if(isUserExist){
            User user=userService.findByUserName(userName,password);
            user.setLastIP(request.getLocalAddr());
            user.setLastVisit(new Date());
            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }else{
            return new ModelAndView("login","error","用户名或密码错误");
        }
    }
}
