package com.lanpangzi.utils.myutils;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyJsonForm {
    private String code;
    private String message;
    private Map<String,Object> result;


    public MyJsonForm(String code, String message) {
        this.result =new LinkedHashMap<String,Object>();
        this.code = code;
        this.message = message;
    }
    public MyJsonForm() {
        this.result =new LinkedHashMap<String,Object>();
    }
    public void setCodeAndMessage(String code ,String message){
        this.code=code;
        this.message=message;
    }
    public void addData(String key, Object value){
        result.put(key,value);
    }
    public Map<String, Object> getResult() {
        return result;
    }
    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
