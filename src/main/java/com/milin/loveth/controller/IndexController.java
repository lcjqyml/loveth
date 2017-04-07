package com.milin.loveth.controller;

import com.milin.loveth.models.Constants;
import com.milin.loveth.models.Menus;
import com.milin.loveth.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 * Created by Changjun Liao on 2016/10/11.
 */
@Controller
public class IndexController extends BaseController {

    @Value("${manage.username}")
    private String username;
    @Value("${manage.password}")
    private String password;

    @RequestMapping("toLogin.html")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("manage.html")
    public String login(Map<String, Object> paramMap) {
        paramMap.put("menu", Menus.img.name());
        return "manage";
    }

    @RequestMapping("loginCheck.do")
    @ResponseBody
    public String login(String username, String password, HttpSession session) {
        if (username.equals(this.username) && password.equals(this.password)) {
            User user = new User();
            user.setPassword(this.password);
            user.setUsername(this.username);
            session.setAttribute(Constants.SESSION_USER, user);
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping("logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.SESSION_USER);
        return "login";
    }

}
