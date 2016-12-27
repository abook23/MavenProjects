package com.grp.ln.web.model;

import java.util.List;

public class MediaMsg {
    private String mmsgId;

    private String userId;

    private String title;

    private String res_ids;

    private String content;

    private Integer readNumber;
    
    private List<Message> messages;//评论
    
    

    public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getMmsgId() {
        return mmsgId;
    }

    public void setMmsgId(String mmsgId) {
        this.mmsgId = mmsgId == null ? null : mmsgId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRes_ids() {
        return res_ids;
    }

    public void setRes_ids(String res_ids) {
        this.res_ids = res_ids == null ? null : res_ids.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }
}