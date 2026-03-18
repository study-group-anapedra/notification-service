package com.luizalebs.notification_service.service.impl;


import com.luizalebs.notification_service.domain.models.NotificationLog;
import com.luizalebs.notification_service.repository.DynamoNotificationRepository;
import com.luizalebs.notification_service.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private final DynamoNotificationRepository repository;

    public LogServiceImpl(DynamoNotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveLog(NotificationLog log) {
        repository.save(log);
    }
}