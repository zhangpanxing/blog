package me.zbl.fullstack.api.controller;

import com.alibaba.fastjson.JSONObject;
import me.zbl.fullstack.entity.User;
import me.zbl.fullstack.service.impl.UserServiceImpl;
import me.zbl.fullstack.utils.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.io.Serializable;


/**
 * 微信小程序控制器
 *
 * @author Freedom
 */
@RestController
@RequestMapping("wx_open_user")
public class WxOpenUserController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(WxOpenUserController.class);

    @Autowired
    private UserServiceImpl userService;

    /**
     * 登录注册
     * @param
     */
    @ResponseBody
    @GetMapping(value="/login")
    public User login(String code,String avatarUrl,String nickName) {
        logger.info("小程序登陆Code:{}",code);

        JSONObject jsonObject = WeChatUtil.getOpenId(code);

        // 我们需要的openid，在一个小程序中，openid是唯一的
        String openid = jsonObject.get("openid").toString();
        User user = userService.forOpenIdFindUser(openid);


        if(user == null){
            if(avatarUrl == null || nickName == null){
                return null;
            }
            User user1 = new User();
            user1.setOpenId(openid);
            user1.setAvatar(avatarUrl);
            user1.setNickname(nickName);
            userService.insertUser(user1);
            return user1;
        }else {
            return user;
        }

    }

    /**
     * 查看用户是否注册
     * @param code
     * @return
     */
    @ResponseBody
    @GetMapping(value="/is_register")
    public Boolean isRegister(String code){
        JSONObject jsonObject = WeChatUtil.getOpenId(code);
        // 我们需要的openid，在一个小程序中，openid是唯一的
        String openid = jsonObject.get("openid").toString();
        User user = userService.forOpenIdFindUser(openid);
        if(user == null){
            return false;
        }else{
            return true;
        }
    }

}
