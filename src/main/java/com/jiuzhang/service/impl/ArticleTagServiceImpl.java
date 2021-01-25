package com.jiuzhang.service.impl;

import com.jiuzhang.dao.ArticleTagDao;
import com.jiuzhang.pojo.ArticleTag;
import com.jiuzhang.service.ArticleTagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
@Service
public class ArticleTagServiceImpl extends BaseServiceImpl<ArticleTag, String> implements ArticleTagService {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    protected JpaRepository getRepository() {
        return articleTagDao;
    }
}
