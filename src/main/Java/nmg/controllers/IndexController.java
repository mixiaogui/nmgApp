package nmg.controllers;


import nmg.configs.WebSecurityConfig;
import nmg.entitys.UserEntity;
import nmg.mappers.UserMapperAn;
import nmg.mappers.UserMapperXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 控制器 博客出处：http://www.cnblogs.com/GoodHelper/
 *
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapperXML userMapper;
    @Autowired
    private UserMapperAn userMapperAn;
    @RequestMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
        model.addAttribute("name", account);
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginPost")
    public @ResponseBody
    Map<String, Object> loginPost(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        UserEntity user = userMapper.getOneByName(account);
        if(user == null ) {
            map.put("success", false);
            map.put("message", "用户名不存在");
            List<UserEntity> users=userMapperAn.getAll();
            map.put("list",users);
            return map;
        }else{
            System.out.println("###loginPost 被调用 ！！！getPassWord :"+ user.getPassWord() +"  username:"+user.getUserName());
        }
        if (! user.getPassWord().equals(password)) {
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, account);

        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}