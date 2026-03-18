package com.luizalebs.notification_service.repository;

import com.luizalebs.notification_service.domain.models.NotificationLog;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DynamoNotificationRepository {

    private final DynamoDbAsyncClient dynamoDbAsyncClient;

    public DynamoNotificationRepository(DynamoDbAsyncClient dynamoDbAsyncClient) {
        this.dynamoDbAsyncClient = dynamoDbAsyncClient;
    }

    public void save(NotificationLog log) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(log.getId()).build());
        item.put("recipient", AttributeValue.builder().s(log.getRecipient()).build());
        item.put("channel", AttributeValue.builder().s(log.getChannel()).build());
        item.put("status", AttributeValue.builder().s(log.getStatus()).build());
        item.put("timestamp", AttributeValue.builder().s(log.getTimestamp()).build());

        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("LogsNotificacao")
                .item(item)
                .build();

        dynamoDbAsyncClient.putItem(putItemRequest);
    }
}