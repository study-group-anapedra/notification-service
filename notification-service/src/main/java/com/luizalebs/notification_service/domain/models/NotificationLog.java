package com.luizalebs.notification_service.domain.models;

import java.time.Instant;

public class NotificationLog {
    private String id;
    private String recipient;
    private String channel;
    private String status;
    private String timestamp;

    public NotificationLog() {
    }

    public NotificationLog(String id, String recipient, String channel, String status, String timestamp) {
        this.id = id;
        this.recipient = recipient;
        this.channel = channel;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}