package com.jiuzhang.control;

import com.jiuzhang.common.utils.JwtTokenUtil;
import com.jiuzhang.rpcdomain.common.RespResult;
import com.jiuzhang.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author by plain yuan
 * @since 2020/04/16
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/center")
    @ResponseBody
    public RespResult accountCenter(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userService.getAccountCenterInfo(userId);
    }
}