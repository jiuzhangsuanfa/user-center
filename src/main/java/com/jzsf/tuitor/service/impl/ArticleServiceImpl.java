package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.common.utils.BeanUtil;
import com.jzsf.tuitor.common.utils.UUIDUtil;
import com.jzsf.tuitor.dao.ArticleDao;
import com.jzsf.tuitor.dao.ArticleTagDao;
import com.jzsf.tuitor.pojo.Article;
import com.jzsf.tuitor.pojo.ArticleTag;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.common.ResultCode;
import com.jzsf.tuitor.rpcDomain.req.ArticleReq;
import com.jzsf.tuitor.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, String>
        implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    protected JpaRepository getRepository() {
        return articleDao;
    }

    @Override
    public RespResult publishArticle(ArticleReq req, String userId) {
        List<String> validateMsg = BeanUtil.validateProperty(req);
        if (validateMsg.size() > 0) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, validateMsg);
        }

        Article article = new Article();
        BeanUtils.copyProperties(req, article);
        article.setUserId(userId);
        article.setId(UUIDUtil.getUUID());
        articleDao.save(article);

        ArticleTag articleTag;
        for (String tagName : req.getTagNameList()) {
            articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagName(tagName);
            articleTagDao.save(articleTag);
        }

        return new RespResult(ResultCode.SUCCESS);
    }
}
