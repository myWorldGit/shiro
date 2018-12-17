package com.lanpangzi.controller;

import com.lanpangzi.mapper.user.UserDao;
import com.lanpangzi.utils.myutils.MyJsonForm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Test {
    @Autowired
    private UserDao userDao;

    @ResponseBody
    @GetMapping("test")
    public MyJsonForm getMapper(){
        MyJsonForm form =new MyJsonForm();
        form.addData("user","usernameinfo");
        form.setCodeAndMessage("1","msg");
        return form;
    }

    @PostMapping("loginauth")
    public String loginAuth(String username,String password){
        System.out.println("jiogn");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            System.out.println("first");
            return "first";
        }catch (Exception e){
            System.out.println("dasd");
            return "authsuccess";
        }

    }

}
