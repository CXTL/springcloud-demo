package com.dupake.system.controller;

import com.dupake.common.message.BaseResult;
import com.dupake.common.message.Result;
import com.dupake.system.service.LoginRequest;
import com.dupake.system.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description TODO
 * @createTime 2020年05月17日 23:08:00
 */
@RestController
@RequestMapping("/admin")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private LoginService loginService;


//    @GetMapping("/")
//    public String showHome() {
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        logger.info("当前登陆用户：" + name);
//
//        return "home.html";
//    }
//
//    @GetMapping("/login")
//    public String showLogin() {
//        return "login.html";
//    }

//    @RequestMapping("/admin")
//    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String printAdmin() {
//        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
//    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasPermission('/admin','r')")
    public String printAdminR() {
        return "如果你看见这句话，说明你访问/admin路径具有r权限";
    }

    @GetMapping("/admin/c")

    @PreAuthorize("hasPermission('/admin','c')")
    public String printAdminC() {
        return "如果你看见这句话，说明你访问/admin路径具有c权限";
    }

    @GetMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalid() {
        return "Session 已过期，请重新登录";
    }


    @GetMapping("/me")
    public Object me(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }


    @GetMapping("/sms/code")
    public void sms(String mobile, HttpSession session) {
        int code = (int) Math.ceil(Math.random() * 9000 + 1000);

        Map<String, Object> map = new HashMap<>(16);
        map.put("mobile", mobile);
        map.put("code", code);

        session.setAttribute("smsCode", map);

        logger.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
    }

    /**
     * 登录返回token
     */
    @PostMapping("/login")
    public Result login(LoginRequest loginRequest) {

        try {
            Map map = loginService.login(loginRequest);
            return Result.ok(BaseResult.SUCCESS.getCode(), "登录成功", map);
        } catch (UsernameNotFoundException e) {
            return Result.error(BaseResult.ERROR.getCode(), "登录失败，用户名或密码错误");
        } catch (RuntimeException re) {
            return Result.error(BaseResult.ERROR.getCode(), re.getMessage());
        }
    }

}
