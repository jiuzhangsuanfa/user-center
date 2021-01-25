package com.jiuzhang.control;

import java.util.List;

import com.jiuzhang.common.utils.BeanUtil;
import com.jiuzhang.common.utils.JwtTokenUtil;
import com.jiuzhang.rpcdomain.common.RespResult;
import com.jiuzhang.rpcdomain.common.ResultCode;
import com.jiuzhang.rpcdomain.req.UserPreferenceReq;
import com.jiuzhang.rpcdomain.req.UserProfileReq;
import com.jiuzhang.service.UserPreferenceService;
import com.jiuzhang.service.UserProfileService;
import com.jiuzhang.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by plain yuan
 * @since 2020/04/13 账户操作控制器
 */
@CrossOrigin
@Controller
@RequestMapping("/account/settings")
public class AccountSettingController {

    private Logger logger = LoggerFactory.getLogger(AccountSettingController.class);

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/notice/show", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult showNoticeConfig(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.getByUserId(userId);
    }

    @PostMapping(value = "/notice/update", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult updateNoticeConfig(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
            @RequestBody UserPreferenceReq noticeConfigReq) {
        List<String> validateMsg = BeanUtil.validateProperty(noticeConfigReq);
        if (validateMsg.size() > 0) {
            return new RespResult<List<String>>(ResultCode.PARAM_IS_BLANK, validateMsg);
        }
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.updateSetting(noticeConfigReq, userId);
    }

    @PostMapping(value = "/profile/show", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult showProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userProfileService.getUserProfileInfo(userId);
    }

    @PostMapping(value = "/profile/update", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult updateProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
            @RequestBody UserProfileReq userProfileReq) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        List<String> validateMsg = BeanUtil.validateProperty(userProfileReq);
        if (validateMsg.size() > 0) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, validateMsg);
        }
        return userProfileService.updateUserProfile(userId, userProfileReq);
    }

}
