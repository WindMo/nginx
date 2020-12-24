package com.nginx.nginx.packageA;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author WindShadow
 * @date 2020/12/16
 * @since
 */
@Slf4j
public class BeanFilter implements TypeFilter{

    @PostConstruct
    public void init() {
        log.info("BeanFilter init");
    }

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        String className = metadataReader.getClassMetadata().getClassName();
        log.info("className: {}",className);
        String packageName = ClassUtils.getPackageName(className);
        log.info("pageName: {}",packageName);
        String targetPackage = "com.nginx.nginx.packageA.pb";

        if (BeanFilter.class.getName().equals(className)){

            return false;
        }
        return targetPackage.equals(packageName);
    }

    public static void main(String[] args) {

        JSONObject object = (JSONObject) JSON.toJSON("aaa");
        System.out.println(object);
    }
}
