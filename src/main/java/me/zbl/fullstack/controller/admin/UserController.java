package me.zbl.fullstack.controller.admin;

import me.zbl.fullstack.controller.base.BaseController;
import me.zbl.fullstack.entity.User;
import me.zbl.fullstack.service.api.IUserService;
import me.zbl.fullstack.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("AdminUserController")
@RequestMapping("/admin")
public class UserController extends BaseController {

    @Autowired
    private IUserService mUserService;
    /**
     * 获取微信用户
     * @return
     */
    @GetMapping("/wx_open_users_manage")
    public String wxOpenUsersManage(){
//        List<User> userList = mUserService.getAllWxOpenUser();

        return  "admin/wx_open_users_manage";
    }

    @ResponseBody
    @GetMapping("/wx_open_user_list.j")
    public Object wxOpenUsers(){
        List<User> userList = mUserService.getAllWxOpenUser();
        return userList;
    }
}
