package com.jiuzhang.usercenter.control;

import javax.validation.constraints.NotNull;

import com.jiuzhang.usercenter.common.utils.JwtTokenUtil;
import com.jiuzhang.usercenter.rpcdomain.common.RespResult;
import com.jiuzhang.usercenter.rpcdomain.common.ResultCode;
import com.jiuzhang.usercenter.rpcdomain.req.ArticleReq;
import com.jiuzhang.usercenter.rpcdomain.req.PageInfoReq;
import com.jiuzhang.usercenter.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by plain yuan
 * @since 2020/04/14 文章操作控制器
 */
@CrossOrigin
@Controller
@RequestMapping(path = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult publish(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
            @RequestBody ArticleReq req) {
        // todo 文章标签和文章的保存这样耦合，是否合适
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return articleService.publishArticle(req, userId);
    }

    @DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult delete(@RequestBody @NotNull ArticleReq req) {
        return articleService.deleteArticleByArticleId(req.getId());
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult update(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
            @RequestBody ArticleReq req) {
        return articleService.updateArticleByAuthor(req, JwtTokenUtil.getUserIdByAuthorHead(headerValue));
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult show(@RequestBody ArticleReq req) {
        return articleService.showSingleArticle(req);
    }

    @GetMapping(value = "list", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RespResult getArticleListByPageAble(@RequestBody @Validated PageInfoReq req) {
        Sort sort = new Sort(req.isDESC() ? Sort.Direction.DESC : Sort.Direction.ASC, req.getProperty());
        Pageable pageable = new PageRequest(req.getPage(), req.getSize(), sort);
        return new RespResult<>(ResultCode.SUCCESS, articleService.findAll(pageable));
    }

}
