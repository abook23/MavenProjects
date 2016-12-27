package com.grp.ln.web.model;

public class MediamsgMessage {
    private String msgId;

    private String mmsgId;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getMmsgId() {
        return mmsgId;
    }

    public void setMmsgId(String mmsgId) {
        this.mmsgId = mmsgId == null ? null : mmsgId.trim();
    }
}