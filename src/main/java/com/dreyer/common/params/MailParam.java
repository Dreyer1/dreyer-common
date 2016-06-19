package com.dreyer.common.params;

import com.dreyer.common.entity.BaseEntity;

/**
 * @author: Dreyer
 * @date: 16/6/14 上午11:23
 * @description: 邮件发送参数类
 */
public class MailParam extends BaseEntity {
    /**
     * 发件人
     **/
    private String from;
    /**
     * 收件人
     **/
    private String to;
    /**
     * 主题
     **/
    private String subject;
    /**
     * 邮件内容
     **/
    private String content;

    public MailParam() {

    }

    public MailParam(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public MailParam(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
