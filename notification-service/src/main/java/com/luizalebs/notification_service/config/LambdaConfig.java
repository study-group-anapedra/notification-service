package com.luizalebs.notification_service.config;


import com.luizalebs.notification_service.domain.dto.MessageResponse;
import com.luizalebs.notification_service.domain.dto.NotificationRequest;
import com.luizalebs.notification_service.service.NotificationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;

@Configuration
public class LambdaConfig {

    private final List<NotificationStrategy> strategies;

    // Construtor manual para injeção de dependência (Sem Lombok)
    public LambdaConfig(List<NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    @Bean
    public Function<NotificationRequest, MessageResponse> notificationFunction() {
        return request -> {
            return strategies.stream()
                    .filter(strategy -> strategy.supports(request.getChannel()))
                    .findFirst()
                    .map(strategy -> strategy.send(request))
                    .orElseGet(() -> new MessageResponse("Erro: Canal " + request.getChannel() + " não suportado.", false));
        };
    }
}