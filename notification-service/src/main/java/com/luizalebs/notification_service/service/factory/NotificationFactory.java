package com.luizalebs.notification_service.service.factory;

import com.luizalebs.notification_service.service.NotificationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationFactory {

    private final List<NotificationStrategy> strategies;

    // Construtor manual para injeção de dependência
    public NotificationFactory(List<NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    public NotificationStrategy getStrategy(String channel) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(channel))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Canal de notificação não suportado: " + channel));
    }
}