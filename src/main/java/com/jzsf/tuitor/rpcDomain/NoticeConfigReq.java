package com.jzsf.tuitor.rpcDomain;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
public class NoticeConfigReq {

    private String userId;
    private String todoNotice;
    private String sysMessageNotice;
    private String otherUserMessageNotice;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTodoNotice() {
        return todoNotice;
    }

    public void setTodoNotice(String todoNotice) {
        this.todoNotice = todoNotice;
    }

    public String getSysMessageNotice() {
        return sysMessageNotice;
    }

    public void setSysMessageNotice(String sysMessageNotice) {
        this.sysMessageNotice = sysMessageNotice;
    }

    public String getOtherUserMessageNotice() {
        return otherUserMessageNotice;
    }

    public void setOtherUserMessageNotice(String otherUserMessageNotice) {
        this.otherUserMessageNotice = otherUserMessageNotice;
    }
}
