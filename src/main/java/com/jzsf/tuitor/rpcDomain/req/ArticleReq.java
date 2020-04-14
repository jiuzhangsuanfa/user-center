package com.jzsf.tuitor.rpcDomain.req;

import java.util.Date;
import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
public class ArticleReq {

    private Date publishTime;
    private String title;
    private String content;
    private List<String> tagNameList;

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTagNameList() {
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }
}
