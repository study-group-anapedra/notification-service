package com.luizalebs.notification_service.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luizalebs.notification_service.domain.dto.NotificationRequest;
import com.luizalebs.notification_service.domain.dto.MessageResponse;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.function.Function;

public class NotificationServiceHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    
    // Inicializa o contexto do Spring UMA ÚNICA VEZ para performance (Cold Start)
    private static final ApplicationContext springContext = 
            new AnnotationConfigApplicationContext("com.luizalebs.notification_service");

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        try {
            String path = request.getPath();
            context.getLogger().log("Requisição recebida no path: " + path);

            // Verifica se o corpo está vazio
            if (request.getBody() == null || request.getBody().isBlank()) {
                return response(400, "{\"error\":\"O corpo da requisição não pode ser vazio\"}");
            }

            // 1. Converte o JSON do API Gateway para o seu DTO de Notificação
            NotificationRequest notificationRequest = mapper.readValue(request.getBody(), NotificationRequest.class);

            // 2. Busca a função do Spring (aquela do seu LambdaConfig)
            @SuppressWarnings("unchecked")
            Function<NotificationRequest, MessageResponse> function = 
                    springContext.getBean("notificationFunction", Function.class);

            // 3. Executa a lógica de negócio
            MessageResponse result = function.apply(notificationRequest);

            // 4. Retorna a resposta adequada
            int statusCode = result.isSuccess() ? 200 : 400;
            return response(statusCode, mapper.writeValueAsString(result));

        } catch (Exception e) {
            context.getLogger().log("ERRO NA LAMBDA: " + e.getMessage());
            return response(500, "{\"error\":\"Erro interno ao processar notificação: " + e.getMessage() + "\"}");
        }
    }

    private APIGatewayProxyResponseEvent response(int status, String body) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(status)
                .withBody(body)
                .withHeaders(Map.of(
                        "Content-Type", "application/json",
                        "Access-Control-Allow-Origin", "*"
                ));
    }
}