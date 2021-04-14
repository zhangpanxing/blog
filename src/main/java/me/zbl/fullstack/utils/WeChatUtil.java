package me.zbl.fullstack.utils;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;


import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class WeChatUtil {

    @Value("wxd494462d567edae8")
    private static String appid = "wxd494462d567edae8";
    @Value("d84f6cb7e4009d62fce444e99e361a75")
    private static String AppSecret = "d84f6cb7e4009d62fce444e99e361a75";

    public static String httpRequest(String requestUrl,String requestMethod,String output){
        try{
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            if(null != output){
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(output.getBytes("utf-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            connection.disconnect();
            return buffer.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static JSONObject getOpenId(String code){

        // 根据小程序穿过来的code想这个url发送请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + AppSecret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求，返回Json字符串
        String str = WeChatUtil.httpRequest(url, "GET", null);
        // 转成Json对象 获取openid
        JSONObject jsonObject = JSONObject.parseObject(str);
        if(jsonObject.isEmpty()){
            return null;
        }
        return jsonObject;

    }

    /**
     * 输入自己的id跟密码，获取微信的安全密令字符串
     * @return
     */
    public static String getAccess_token() {
        //设置变量 url与返回值其中url使用拼接带入参数APP_ID， APPSECRET
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appid+ "&secret=" + AppSecret;
        String accessToken = null;
        try {

             String message = HttpUtils.getRequest(url);
//            转化成json对象然后返回accessToken属性的值
            JSONObject demoJson = JSONObject.parseObject(message);
            accessToken = demoJson.getString("access_token");
            System.out.println(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public static void main(String [] arr){
        System.out.println(getAccess_token());
    }

}
