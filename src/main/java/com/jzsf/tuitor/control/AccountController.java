package com.jzsf.tuitor.control;

import com.jzsf.tuitor.common.token.Audience;
import com.jzsf.tuitor.common.token.JwtTokenUtil;
import com.jzsf.tuitor.common.utils.BeanUtil;
import com.jzsf.tuitor.rpcDomain.NoticeConfigReq;
import com.jzsf.tuitor.rpcDomain.RespResult;
import com.jzsf.tuitor.rpcDomain.ResultCode;
import com.jzsf.tuitor.service.UserPreferenceService;
import com.jzsf.tuitor.service.UserProfileService;
import com.jzsf.tuitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @Autowired
    private Audience audience;

    @PostMapping(value = "/settings/notice/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showNoticeConfig(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String token) {
        String userId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());
        return userPreferenceService.getByUserId(userId);
    }

    @PostMapping(value = "/settings/notice/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult updateNoticeConfig(@RequestBody NoticeConfigReq noticeConfigReq) {
        List<String> validateMsg = BeanUtil.validateProperty(noticeConfigReq);
        if (validateMsg.size() > 0) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, validateMsg);
        }
        return userPreferenceService.updateSetting(noticeConfigReq);
    }

    @PostMapping(value = "/settings/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String token) {
        String userId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());
        return userProfileService.getUserProfileInfo(userId);
    }

}
