package com.luizalebs.notification_service.service.impl;

import com.luizalebs.notification_service.domain.dto.MessageResponse;
import com.luizalebs.notification_service.domain.dto.NotificationRequest;
import com.luizalebs.notification_service.domain.models.NotificationLog;
import com.luizalebs.notification_service.service.LogService;
import com.luizalebs.notification_service.service.NotificationStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class EmailNotificationServiceImpl implements NotificationStrategy {

    private final JavaMailSender mailSender;
    private final LogService logService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailNotificationServiceImpl(JavaMailSender mailSender, LogService logService) {
        this.mailSender = mailSender;
        this.logService = logService;
    }

    @Override
    public MessageResponse send(NotificationRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(request.getRecipient());
            message.setSubject("Nova Notificação do Sistema");
            message.setText(request.getContent());

            mailSender.send(message);

            saveInternalLog(request, "SUCESSO");
            return new MessageResponse("E-mail enviado com sucesso para: " + request.getRecipient(), true);
        } catch (Exception e) {
            saveInternalLog(request, "ERRO");
            return new MessageResponse("Erro ao enviar e-mail: " + e.getMessage(), false);
        }
    }

    @Override
    public boolean supports(String channel) {
        return "EMAIL".equalsIgnoreCase(channel);
    }

    private void saveInternalLog(NotificationRequest request, String status) {
        NotificationLog log = new NotificationLog(
                UUID.randomUUID().toString(),
                request.getRecipient(),
                request.getChannel(),
                status,
                Instant.now().toString()
        );
        logService.saveLog(log);
    }
}