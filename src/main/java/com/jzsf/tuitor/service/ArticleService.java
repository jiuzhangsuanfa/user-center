package com.jzsf.tuitor.service;

import com.jzsf.tuitor.pojo.Article;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.req.ArticleReq;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
@Service
public interface ArticleService extends BaseService<Article, String> {

    /**
     * 发布新文章
     *
     * @param req
     * @param userId
     * @return
     */
    RespResult publishArticle(ArticleReq req, String userId);

}
