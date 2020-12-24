package com.nginx.nginx.packageA;

import com.nginx.nginx.packageA.pb.IntF;
import com.nginx.nginx.packageA.pb.PbBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author WindShadow
 * @date 2020/12/16
 * @since
 */

@ComponentScan(includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = BeanFilter.class)})
@Configuration
public class AConfig {

}
