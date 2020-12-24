package com.nginx.nginx.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

/**
 * @author WindShadow
 * @date 2020/12/15
 * @since
 */
@Component
public class Tb extends TaskA{

    @Autowired
    private TempBean tempBean;

    @PostConstruct
    public void init() {

        System.out.println(tempBean == null);
    }

    public static void main(String[] args) {

        for(Field field : Tb.class.getDeclaredFields()){

            System.out.println(field.getName());
        }
    }
}
