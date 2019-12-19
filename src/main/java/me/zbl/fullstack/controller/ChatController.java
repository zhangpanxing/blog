package me.zbl.fullstack.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import me.zbl.fullstack.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatController extends BaseController {

    @GetMapping("/chat")
    public String jumpChat(HttpServletRequest request, Model model){

        return "chat";
    }

}
