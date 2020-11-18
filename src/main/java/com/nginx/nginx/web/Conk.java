package com.nginx.nginx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WindShadow
 * @version 2020/11/18.
 */

@Controller
public class Conk {

    @RequestMapping("/ppp")
    public String m2() {

        return "redirect:/iii?a=i";
    }

    @RequestMapping("/pp")
    public String ms2() {

        return "redirect:/iii";
    }
}
