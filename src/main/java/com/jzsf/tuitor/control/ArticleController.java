package com.jzsf.tuitor.control;

import com.jzsf.tuitor.common.utils.JwtTokenUtil;
import com.jzsf.tuitor.rpcdomain.common.RespResult;
import com.jzsf.tuitor.rpcdomain.common.ResultCode;
import com.jzsf.tuitor.rpcdomain.req.ArticleReq;
import com.jzsf.tuitor.rpcdomain.req.PageInfoReq;
import com.jzsf.tuitor.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author by plain yuan
 * @since 2020/04/14
 * 文章操作控制器
 */
@CrossOrigin
@Controller
@RequestMapping("/article")
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(AccountSettingController.class);

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/publish", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult publish(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                              @RequestBody ArticleReq req) {
        // todo 文章标签和文章的保存这样耦合，是否合适
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return articleService.publishArticle(req, userId);
    }

    @RequestMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult delete(@RequestBody @NotNull ArticleReq req) {
        return articleService.deleteArticleByArticleId(req.getId());
    }

    @RequestMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult update(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                             @RequestBody ArticleReq req) {
        return articleService.updateArticleByAuthor(req, JwtTokenUtil.getUserIdByAuthorHead(headerValue));
    }

    @RequestMapping(value = "/show/detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult show(@RequestBody ArticleReq req) {
        return articleService.showSingleArticle(req);
    }

    @RequestMapping(value = "/show/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult getArticleListByPageAble(@RequestBody @Validated PageInfoReq req) {
        Sort sort = new Sort(req.isDESC() ? Sort.Direction.DESC : Sort.Direction.ASC, req.getProperty());
        Pageable pageable = new PageRequest(req.getPage(), req.getSize(), sort);
        return new RespResult(ResultCode.SUCCESS, articleService.findAll(pageable));
    }

}

