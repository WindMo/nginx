package com.nginx.nginx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        return null;
    }

    @RequestMapping("/p")
    public String ms21() {

        return "redirect:http://127.0.0.2:8082/demo/iii";
    }

    @RequestMapping("/ll")
    public void ms23(HttpServletResponse response) throws IOException {

        response.sendRedirect("/demo/iii");
    }


    @RequestMapping("/demo")
    public String ms22() {

        return "demodemo";
    }

    @RequestMapping("/bd")
    public void ms2(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = "http://10.1.200.190" + request.getContextPath() + "/iii";
//        String path = "https://www,baidu.com";
//        String path = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=monline_3_dg&wd=nginx%20if%E6%8C%87%E4%BB%A4%E4%BD%9C%E7%94%A8%E5%9F%9F&oq=nginx%2520if%25E6%258C%2587%25E4%25BB%25A4&rsv_pq=d7e164b6000a284d&rsv_t=7b5flMlRTr58Yk0SyLA4WB4JwDElxDm%2BhkyhdpVrwIAmPrbcbXAG4ZR2DvTD%2BUbok%2FWC&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_btype=t&inputT=1794&rsv_sug3=20&rsv_sug1=5&rsv_sug7=000&rsv_sug2=0&rsv_sug4=3042&rsv_sug=1";
//        String path = "http:180.101.49.11/s?ie=utf-8&f=8&rsv_bp=1&tn=monlin";
        response.sendRedirect(path);
    }
}
