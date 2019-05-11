package com.course.server;

        import com.course.bean.User;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

/**
 * @author shkstart
 * @Date 2019/4/24 - 18:58
 */
@RestController
@Api(value = "/",description = "这是我全部的post接口")
@RequestMapping("/v1")
public class MyPostMethod {

    private Cookie cookie;

    //携带参数做登录操作，并返回Cookies信息
    @RequestMapping(value = "/post/with/param",method = RequestMethod.POST)
    @ApiOperation(value = "这是模拟用户用post方式登录，携带参数用户名和密码，并返回cookies",httpMethod = "POST")
    public String postWithParam(@RequestParam(value = "战网客户端",required = true) String username,
                              @RequestParam(value = "战网密令",required = true) String password,
                              HttpServletResponse response){
        if(username.equals("叶超") && password.equals("123456")){
            cookie = new Cookie("login", "success");
            response.addCookie(cookie);
            return "恭喜你，登录成功";
        }
        return "用户名或密码错误";
    }

    //登录成功后获取用户列表
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "登录成功后，获取用户列表",httpMethod = "POST")
    public String  getUserList(HttpServletRequest request,
                            @RequestBody User user){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login")
                    && cookie.getValue().equals("success")
                    && user.getUsername().equals("叶超")
                    && user.getPassword().equals("123456")){

                System.out.println("1111");
                User user1 = new User();
                user1.setName("yechao");
                user1.setSex("male");
                user1.setAge("18");
                return user1.toString();
            }
        }
        return "验证信息不通过，无法获取用户列表";
    }
}
