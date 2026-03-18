package com.luizalebs.notification_service.service.impl;


import com.luizalebs.notification_service.domain.dto.MessageResponse;
import com.luizalebs.notification_service.domain.dto.NotificationRequest;
import com.luizalebs.notification_service.domain.models.NotificationLog;
import com.luizalebs.notification_service.service.LogService;
import com.luizalebs.notification_service.service.NotificationStrategy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class SmsNotificationServiceImpl implements NotificationStrategy {

    private final LogService logService;

    public SmsNotificationServiceImpl(LogService logService) {
        this.logService = logService;
    }

    @Override
    public MessageResponse send(NotificationRequest request) {
        // Mock de envio de SMS
        System.out.println("SIMULANDO ENVIO DE SMS PARA: " + request.getRecipient());
        
        NotificationLog log = new NotificationLog(
                UUID.randomUUID().toString(),
                request.getRecipient(),
                request.getChannel(),
                "SUCESSO_MOCK",
                Instant.now().toString()
        );
        logService.saveLog(log);

        return new MessageResponse("SMS Simulado com sucesso!", true);
    }

    @Override
    public boolean supports(String channel) {
        return "SMS".equalsIgnoreCase(channel);
    }
}