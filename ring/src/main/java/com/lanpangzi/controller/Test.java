package com.lanpangzi.controller;

import com.lanpangzi.mapper.user.UserDao;
import com.lanpangzi.utils.myutils.MyJsonForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("getinfo")
    public String getinfo(){
        return "first";
    }

}
