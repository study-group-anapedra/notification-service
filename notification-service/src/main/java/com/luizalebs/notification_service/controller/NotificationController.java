package com.luizalebs.notification_service.controller;

import com.luizalebs.notification_service.domain.dto.MessageResponse;
import com.luizalebs.notification_service.domain.dto.NotificationRequest;
import com.luizalebs.notification_service.service.NotificationStrategy;
import com.luizalebs.notification_service.service.factory.NotificationFactory;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final NotificationFactory notificationFactory;

    public NotificationController(NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> send(@Valid @RequestBody NotificationRequest request) {

        NotificationStrategy strategy =
                notificationFactory.getStrategy(request.getChannel());

        MessageResponse response = strategy.send(request);

        HttpStatus status =
                response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(response);
    }
}