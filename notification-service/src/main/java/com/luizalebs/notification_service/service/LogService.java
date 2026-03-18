package com.luizalebs.notification_service.service;


import com.luizalebs.notification_service.domain.models.NotificationLog;

public interface LogService {
    void saveLog(NotificationLog log);
}