package com.luizalebs.notification_service.service;


import com.luizalebs.notification_service.domain.dto.MessageResponse;
import com.luizalebs.notification_service.domain.dto.NotificationRequest;

public interface NotificationStrategy {
    MessageResponse send(NotificationRequest request);
    boolean supports(String channel);
}