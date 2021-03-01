package com.nginx.nginx.web;

import magic.common.util.web.BaseDownloadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author WindShadow
 * @date 2021/1/6
 * @since 1.0.0
 */

@Controller
public class FileDownload {

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {

        File file = new File("E:\\my-java-project\\MyJavaWorkplace\\nginx\\src\\main\\resources\\templates\\js-redirect.html");
        String fileName = file.getName() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        BaseDownloadUtil.downloadFromFileSupport(file,response,10 * 1024,fileName);
    }

}
