package com.luizalebs.notification_service.domain.dto;

public class NotificationRequest {
    private String recipient;
    private String content;
    private String channel;

    public NotificationRequest() {
    }

    public NotificationRequest(String recipient, String content, String channel) {
        this.recipient = recipient;
        this.content = content;
        this.channel = channel;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}