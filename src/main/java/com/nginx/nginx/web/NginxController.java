package com.nginx.nginx.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * @author WindShadow
 * @version 2020/11/15.
 */

@RestController
public class NginxController {

    @Autowired
    private HttpServletRequest request;

//    @RequestMapping("/{path}")
//    public String m0(@PathVariable("path") String path) {
//
//        return path == null ? "path = null" : path;
//    }

//    @RequestMapping("/")
    public String m0001() {

        return "is /";
    }

    @RequestMapping("")
    public String m0000() {

        return "is empty";
    }


    @RequestMapping("/iii/{path}")
    public String m00(@PathVariable("path") String path) {

        return "iii " + (path == null ? "path = null" : path);
    }

    @RequestMapping("/iii")
    public String m000(String name, HttpServletResponse response) {

//        response.setHeader("hhh",null);
        response.setHeader("hhh2","");
        return "iii - 2" + name;
    }

    @RequestMapping("/index")
    public String m1() {

        return "index";
    }


    @RequestMapping("/bbb")
    public String m3() {

        return "bbb";
    }

    @RequestMapping("/aa/bb")
    public String m4() {

        return "aa/bb";
    }

    @RequestMapping("/get")
    public String m5(String name) {

        return "get?" + (name == null ? "null" : name);
    }

    @RequestMapping("/redirect")
    public String m6() {

        return "redirect:/target";
    }

    @RequestMapping("/target")
    public String target() {

        return "target";
    }

    @RequestMapping("/image/png")
    public String png() {

        return "png";
    }

//    @RequestMapping(value = "/rest")
//    public Map<String,String> rest() {
//
//        Map<String,String> map = Collections.singletonMap("url","http://127.0.0.1:8080/index");
//        return map;
//    }

    @RequestMapping(value = "/rest")
    public String rest(String callback) {

        Map<String,String> map = Collections.singletonMap("url","http://127.0.0.1:8080/index");

        return callback + "("+ JSON.toJSONString(map) + ")";
    }


















    public static void main(String[] args) {

        String callback = "callback";
        Map<String,String> map = Collections.singletonMap("url","http://127.0.0.1:8080/index");

        System.out.println(callback + "("+ JSON.toJSONString(map) + ")");
    }
}
