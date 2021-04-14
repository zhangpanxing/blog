package me.zbl.fullstack.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class TestUtil {

    public static String appid;

    @Value("${tengxunyun.oss.bucket}")
    public  void setAppid(String appid){
        TestUtil.appid = appid;
    }

    public static String AppSecret;
    @Value("${wxOpen.AppSecret}")
    public  void setAppSecret(String AppSecret){
        TestUtil.AppSecret = AppSecret;
    }

    public static void dome(){
        System.out.println(appid);
        System.out.println(AppSecret);
    }
}
