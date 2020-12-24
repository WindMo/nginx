package com.nginx.nginx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nginx.nginx.aop.Target;
import com.nginx.nginx.jicheng.Children;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class NginxApplicationTests {

    @Autowired
    Children children;

    @Autowired
    Target target;

    @Test
    public void contextLoads() {

       children.toSay();
    }

    @Test
    public void contextLoads2() {

        target.todo();
    }


    public static void main(String[] args) throws NoSuchMethodException {

//        System.out.println(NginxApplicationTests.class.getMethod("contextLoads").toString());

        User<String> user = new User();
        user.setName("zs");
        user.setIsTemp(false);
        user.setTt("666");

        Re<User<String>> re = new Re<>(101,user);
//        String ss = JSON.toJSONString(re);
        String ss = "{\"code\":101,\"data\":{\"isTemp\":false,\"name\":\"zs\",\"tt\":\"666\",\"tp\":\"666\"}}";
//        String ss = "{\"code\":101,\"data\":{\"isTemp\":false,\"name\":\"zs\"}}";
//        String ss = "{}";
        JSONObject object = JSON.parseObject(ss);
        System.out.println("jsonobject: " + object.toString());
        Re<User<String>> re2 = object.toJavaObject(new TypeReference<Re<User<String>>>(){});

        User<String> d = re2.getData();
        System.out.println("re2: " + re2);
        System.out.println("re2-data: "+d);
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
class Re<T> {
    public static final String s= "sada";
    private Integer code;
    private T data;
}

@AllArgsConstructor
@NoArgsConstructor
@ToString
class User<T> {

    private String name;
    private Boolean isTemp;
    private T tt;

    public T getTt() {
        return tt;
    }

    public void setTt(T tt) {
        this.tt = tt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Boolean isTemp) {
        this.isTemp = isTemp;
    }
}

class Test2 {

    public static void main(String[] args) {

        JSONObject object = new JSONObject();
        System.out.println(object.toString());
    }
}
