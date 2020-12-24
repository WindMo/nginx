package com.nginx.nginx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public String m000(String name) {

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
}
