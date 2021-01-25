package com.jiuzhang.service;

import java.util.List;

import com.jiuzhang.pojo.Article;
import com.jiuzhang.rpcdomain.common.RespResult;
import com.jiuzhang.rpcdomain.req.ArticleReq;

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

    /**
     * 根据文章id，删除文章
     *
     * @param articleId
     * @return
     */
    RespResult deleteArticleByArticleId(String articleId);

    /**
     * 更新文章
     *
     * @param req
     * @param userId
     * @return
     */
    RespResult updateArticleByAuthor(ArticleReq req, String userId);

    /**
     * 查看文章内容
     *
     * @param req
     * @return
     */
    RespResult showSingleArticle(ArticleReq req);

    /**
     * 查询最近发布的十篇文章
     *
     * @return
     */
    List<Article> getRecentPublishedArticles();

}
