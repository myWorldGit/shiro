package com.lanpangzi.controller;

import com.lanpangzi.conf.service.OssService;
import com.lanpangzi.mapper.user.UserDao;
import com.lanpangzi.utils.myutils.MyJsonForm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class Test {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OssService ossService;


    @ResponseBody
    @PostMapping("test")
    public MyJsonForm getMapper(MultipartFile file){
        MyJsonForm form =new MyJsonForm();

        try {
          String filename= ossService.uploadImgToOss(file);
          String url =  ossService.getImgUrl(filename);
          form.addData("url",url);
        }catch (IOException e){
            e.printStackTrace();
        }
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
