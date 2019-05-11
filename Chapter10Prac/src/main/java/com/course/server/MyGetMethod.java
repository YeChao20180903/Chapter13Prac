package com.course.server;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.testng.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author shkstart
 * @Date 2019/4/23 - 9:56
 */
@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    //服务端返回Cookies信息
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "获取cookies信息接口",httpMethod = "GET")
    public String  getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜获取Cookies信息成功啦！！！";
    }

    //客户端携带Cookies信息访问
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带Cookies信息才能访问的接口",httpMethod = "GET")
    public String getInfoWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            Assert.assertTrue(false);
            return "需要携带Cookies信息才能访问";
        }
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if(name.equals("login") && value.equals("true")){
                return "携带Cookies信息访问成功";
            }
        }
        return "需要携带Cookies信息才能访问";
    }

    //携带参数访问格式一：key:value && key:value
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get接口的第一种实现",httpMethod = "GET")
    public Object getWithParam1(@RequestParam Integer start,
                                            @RequestParam Integer end,
                                             HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Map<String, Integer> list = new HashMap<>();
        list.put("常胜将军--赵子龙",199);
        list.put("KDA/阿狸",199);
        list.put("霸天异形",199);

        if(Objects.isNull(cookies)){
            Assert.assertTrue(false);
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return list;
            }
        }

        return "携带的Cookies信息不正确";

    }

    //携带参数访问格式二：/访问路径/{参数1}/{参数2}/{参数...}
    @ApiOperation(value = "需要携带参数才能访问的get请求实现方法二",httpMethod = "GET")
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    public Map getWithParam2(@PathVariable Integer start,
                             @PathVariable Integer end){
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("替父从军--花木兰",199);
        hashMap.put("光明使者--锐雯",99);
        hashMap.put("未来战士--锐雯",99);
        return hashMap;
    }
}
