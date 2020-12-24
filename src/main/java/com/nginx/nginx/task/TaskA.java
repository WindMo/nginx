package com.nginx.nginx.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author WindShadow
 * @date 2020/12/14
 * @since 1.0
 */

@Slf4j
@Component
@EnableScheduling
public class TaskA {

    @Autowired
    private TempBean tempBean;

//    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() {

        log.info(">>>{} ===定时任务 task1", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
