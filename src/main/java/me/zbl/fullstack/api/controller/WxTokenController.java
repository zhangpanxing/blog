package me.zbl.fullstack.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zbl.fullstack.utils.WeChatUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/wx_token")
public class WxTokenController {
    private static Log log = LogFactory.getLog(WxTokenController.class);
    /**
     * 接收微信后台发来的用户消息
     * @return
     */
    @RequestMapping(value = "/get_token", method = RequestMethod.POST)
    @ResponseBody
    public String receiveMessage(@RequestBody Map<String, Object> msg) {
        log.info(msg);
        /**
         * {ToUserName=gh_8f177295cfc3, FromUserName=okJwd5POdQ5rukX-LE94QZLuBww4,
         * CreateTime=1589082421, MsgType=text, Content=哦哦哦, MsgId=22750189903444485}
         */

        //获取token
        String access_token = WeChatUtil.getAccess_token();
        log.info("access_token:--------" + access_token);
        //用户openId
        String fromUserName = msg.get("FromUserName").toString();
        String createTime = msg.get("CreateTime").toString();
        String toUserName = msg.get("ToUserName").toString();
        String msgType = msg.get("MsgType").toString();
        if (msgType.equals("text")) { //收到的是文本消息,并将消息返回给客服
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("ToUserName", fromUserName);
            resultMap.put("FromUserName", toUserName);
            resultMap.put("CreateTime", Long.parseLong(createTime));
            resultMap.put("MsgType", "transfer_customer_service");
            String json = JSON.toJSONString(resultMap);
            JSONObject result = JSONObject.parseObject(json);
            return result.toString();
        }
        //客服方面,也回复一个文本消息
        JSONObject text = new JSONObject();
        text.fluentPut("content", msg.get("MsgType"));
        JSONObject json = new JSONObject();
        json.fluentPut("touser", toUserName);
        json.fluentPut("text", text);
        json.fluentPut("msgtype", "text");
        //发送aip
        sendMsToCustomer(access_token, json);
        return "success";
    }
    /**
     * 用户发送消息给客服
     *
     * @param json 消息的参数
     */
    private void sendMsToCustomer(String access_token, JSONObject json) {

        RestTemplate restTemplate = new RestTemplate();

        //access_token
        String result = restTemplate.postForEntity("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" +
                access_token, json, String.class).getBody();
        log.info("result:"+result);
    }




    /**
     * 下方注释为验证使用
     */
    // 与接口配置信息中的Token要一致
//    private static String WECHAT_TOKEN = "ZHANGPANXING";
//
//    @RequestMapping("/get_token")
//    public void getToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        boolean isGet = request.getMethod().toLowerCase().equals("get");
//        PrintWriter print;
//        if (isGet) {
//            // 微信加密签名
//            String signature = request.getParameter("signature");
//            // 时间戳
//            String timestamp = request.getParameter("timestamp");
//            // 随机数
//            String nonce = request.getParameter("nonce");
//            // 随机字符串
//            String echostr = request.getParameter("echostr");
//            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
//            if (signature != null && checkSignature(signature, timestamp, nonce)) {
//                log.info("接入成功");
//                try {
//                    print = response.getWriter();
//                    print.write(echostr);
//                    print.flush();
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//
//            }
//        } else {
//
//        }
//        return;
//    }
//    /**
//     * 验证签名
//     *
//     * @param signature
//     * @param timestamp
//     * @param nonce
//     * @return
//     */
//    public static boolean checkSignature(String signature, String timestamp, String nonce) {
//        String[] arr = new String[]{WECHAT_TOKEN, timestamp, nonce};
//        // 将token、timestamp、nonce三个参数进行字典序排序
//        // Arrays.sort(arr);
//        sort(arr);
//        StringBuilder content = new StringBuilder();
//        for (int i = 0; i < arr.length; i++) {
//            content.append(arr[i]);
//        }
//        MessageDigest md = null;
//        String tmpStr = null;
//
//        try {
//            md = MessageDigest.getInstance("SHA-1");
//            // 将三个参数字符串拼接成一个字符串进行sha1加密
//            byte[] digest = md.digest(content.toString().getBytes());
//            tmpStr = byteToStr(digest);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        content = null;
//        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
//        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
//    }
//
//    /**
//     * 将字节数组转换为十六进制字符串
//     *
//     * @param byteArray
//     * @return
//     */
//    private static String byteToStr(byte[] byteArray) {
//        String strDigest = "";
//        for (int i = 0; i < byteArray.length; i++) {
//            strDigest += byteToHexStr(byteArray[i]);
//        }
//        return strDigest;
//    }
//
//    /**
//     * 将字节转换为十六进制字符串
//     *
//     * @param mByte
//     * @return
//     */
//    private static String byteToHexStr(byte mByte) {
//        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//        char[] tempArr = new char[2];
//        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
//        tempArr[1] = Digit[mByte & 0X0F];
//        String s = new String(tempArr);
//        return s;
//    }
//
//    public static void sort(String a[]) {
//        for (int i = 0; i < a.length - 1; i++) {
//            for (int j = i + 1; j < a.length; j++) {
//                if (a[j].compareTo(a[i]) < 0) {
//                    String temp = a[i];
//                    a[i] = a[j];
//                    a[j] = temp;
//                }
//            }
//        }
//    }



}
