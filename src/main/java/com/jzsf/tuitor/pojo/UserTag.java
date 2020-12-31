package com.jzsf.tuitor.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Entity
@Table(name = "user_tag")
public class UserTag implements Serializable {

    private static final long serialVersionUID = -4687245119815613165L;

    @Id
    @Column(name = "id", length = 128, nullable = false)
    private String id;

    @Column(name = "user_id", length = 48)
    private String userId;

    @Column(name = "tag_name", length = 1024)
    private String tagName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
