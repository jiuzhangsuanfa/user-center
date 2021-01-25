package com.jiuzhang.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jiuzhang.base.ValidateException;
import com.jiuzhang.common.stratege.ContextMapper;
import com.jiuzhang.common.stratege.OperatorStrategeEnum;
import com.jiuzhang.common.utils.UUIDUtil;
import com.jiuzhang.component.valitor.ReqValidateManager;
import com.jiuzhang.dao.UserDao;
import com.jiuzhang.pojo.*;
import com.jiuzhang.rpcdomain.common.RespResult;
import com.jiuzhang.rpcdomain.common.ResultCode;
import com.jiuzhang.rpcdomain.req.RegisterReq;
import com.jiuzhang.rpcdomain.resp.ArticleResp;
import com.jiuzhang.rpcdomain.resp.UserCenterDTOResp;
import com.jiuzhang.service.*;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterRecordService registerRecordService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ToolService toolService;

    @Autowired
    private ReqValidateManager reqValidateManager;

    @Autowired
    private ContextMapper contextMapper;

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userDao;
    }

    @Override
    public RespResult beforeRegister(RegisterReq reqInfo) {

        /**
         * 调用validate manager
         */
        try {
            reqValidateManager.doExeute(reqInfo);
        } catch (ValidateException e) {
            return new RespResult(e.getResultCode());
        }

        boolean isSend = toolService.sendRegisterMail(reqInfo);
        OperatorStrategeEnum context;
        if (isSend) {
            context = OperatorStrategeEnum.SUCCESS;
        } else {
            context = OperatorStrategeEnum.EMAILFAIL;
        }

        return contextMapper.loadProcessor(context).doProcessor(reqInfo, context);

    }

    @Override
    public boolean checkCaptcha(RegisterReq reqInfo) {
        String captcha = registerRecordService.getCaptchaByUsername(reqInfo.getUsername());
        return StringUtils.equals(reqInfo.getCaptcha(), captcha);
    }

    @Override
    public RespResult registerUser(RegisterReq reqInfo) {
        User user = userDao.getByUsername(reqInfo.getUsername());
        user.setIsVerified("1");
        userDao.save(user);
        initUserInfo(user);
        return new RespResult(ResultCode.REGISTERED_SUCCESS);
    }

    @Override
    public boolean checkValid(User user) {
        return user != null && StringUtils.equals("1", user.getIsVerified());
    }

    /**
     * 用户注册成功时，初始化新用户的信息
     */
    private void initUserInfo(User user) {
        String userId = user.getId();
        Address address = new Address();
        address.setUserId(userId);

        UserPreference userPreference = new UserPreference();
        userPreference.setUserId(userId);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);

        userPreference.setSysMessageNotice("1");
        userPreference.setTodoNotice("1");
        userPreference.setOtherUserMessageNotice("1");

        UserTag userTag = new UserTag();
        userTag.setId(UUIDUtil.getUUID());
        userTag.setUserId(userId);

        addressService.save(address);
        userPreferenceService.save(userPreference);
        userProfileService.save(userProfile);
        userTagService.save(userTag);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public RespResult getAccountCenterInfo(String userId) {
        UserCenterDTOResp dtoResp = new UserCenterDTOResp();
        Optional<User> optionalUser = userDao.findById(userId);
        if (optionalUser.isPresent()) {
            BeanUtils.copyProperties(optionalUser.get(), dtoResp);
            Address address = addressService.findById(userId).get();
            String res = (address.getProvince() == null ? "" : address.getProvince())
                    + (address.getCity() == null ? "" : address.getCity());
            if (StringUtils.isNotBlank(res)) {
                dtoResp.setProvinceAndCity(res);
            }
            dtoResp.setPersonalProfile(userProfileService.findById(userId).get().getPersonalProfile());
            List<ArticleResp> articleList = new ArrayList<>();
            ArticleResp articleResp;
            for (Article recentPublishedArticle : articleService.getRecentPublishedArticles()) {
                articleResp = new ArticleResp();
                BeanUtils.copyProperties(recentPublishedArticle, articleResp);
                articleList.add(articleResp);
            }
            List<String> userTagList = userTagService.getUserTagList(userId);

            dtoResp.setArticleList(articleList);
            if (userTagList != null && userTagList.size() > 0) {
                dtoResp.setUserTagList(userTagList);
            }

            return new RespResult(ResultCode.SUCCESS, dtoResp);
        } else {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
    }
}
