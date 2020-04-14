package com.jzsf.tuitor.dao;

import com.jzsf.tuitor.pojo.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
@Repository
public interface ArticleTagDao extends JpaRepository<ArticleTag, String> {

}
