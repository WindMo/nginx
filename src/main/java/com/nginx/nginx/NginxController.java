package com.nginx.nginx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WindShadow
 * @version 2020/11/15.
 */

@RestController
@RequestMapping("/nginx")
public class NginxController {

    @Autowired
    private HttpRequest request;

    @RequestMapping("/index")
    public String m1() {

        return "index";
    }

    @RequestMapping("/aaa")
    public String m2() {

        return "aaa";
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
