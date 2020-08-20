package cn.web.management.controller;

import cn.web.management.mapper.UserMapper;
import cn.web.management.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserMapper usermapper;
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    @RequestMapping("/user/login")
    public String login(HttpServletRequest request,Map<String,Object> map){
        String register = request.getParameter("register");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginuser = usermapper.login(username, password);
        if (register!=null){
            return "register";
        }
        else {
            if (loginuser!=null)
            {
                return "home";
            }
            else {
                map.put("msg","用户或密码错误");
                return "login";
            }
        }
    }
    @RequestMapping("/user/register")
    public String register(HttpServletRequest request,Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        User user=usermapper.getuser(username);
        if (user!=null){
            map.put("msg1","该用户已经被注册!");
            return  "register";
        }else {
            if (password.equals(confirm))
            {
                usermapper.addUser(username,password);
                return "login";
            }else {
                map.put("msg1","两次密码输入不一致!");
                return  "register";
            }
        }
    }
    @RequestMapping("/user/home")
    public String home()
    {
        return "home";
    }

    @RequestMapping("/user/choose")
    public String chooser(HttpServletRequest request){
        String index = request.getParameter("index");
        int num=Integer.parseInt(index);
        switch (num){
            case 1:{
                return "tree";
            }
            case 2:{
                return "graph";
            }
            case 3:{
                return "array";
            }
            case 4:{
                return "list";
            }
            case 5:{
                return "queue";
            }
            case 6:{
                return "stack";
            }
        }
        return "test";
    }

}
