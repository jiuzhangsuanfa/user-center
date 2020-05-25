package com.jzsf.tuitor.control;

import com.jzsf.tuitor.common.utils.JwtTokenUtil;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by plain yuan
 * @since 2020/04/16
 */
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
