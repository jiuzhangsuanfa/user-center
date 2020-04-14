package com.jzsf.tuitor.control;

import com.jzsf.tuitor.common.utils.JwtTokenUtil;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.req.ArticleReq;
import com.jzsf.tuitor.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author by plain yuan
 * @since 2020/04/14
 * 文章操作控制器
 */

@RequestMapping("/article")
@Controller
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/publish", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult check(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                            @RequestBody ArticleReq req) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return articleService.publishArticle(req, userId);
    }


}
