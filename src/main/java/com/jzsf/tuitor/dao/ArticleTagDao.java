package com.jzsf.tuitor.dao;

import com.jzsf.tuitor.pojo.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
@Repository
public interface ArticleTagDao extends JpaRepository<ArticleTag, String> {

    /**
     * 根据文章id，获取该文章所有标签
     *
     * @param articleId
     * @return
     */
    @Query(value = "select tagName from ArticleTag where articleId =?1")
    List<String> findTagNameByArticleId(String articleId);

    /**
     * 根据作者id删除文章的所有tag
     *
     * @param articleId
     */
    @Query("delete from ArticleTag  where articleId = ?1")
    void deleteAllTagByArticleId(String articleId);

}
