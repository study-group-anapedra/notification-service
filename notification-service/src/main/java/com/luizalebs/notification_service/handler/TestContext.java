//package com.luizalebs.notification_service.handler;
//
//import com.amazonaws.services.lambda.runtime.ClientContext;
//import com.amazonaws.services.lambda.runtime.CognitoIdentity;
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.LambdaLogger;
//
//public class TestContext implements Context {
//
//    public static Context fake() {
//        return new TestContext();
//    }
//
//    @Override
//    public String getAwsRequestId() { return "test-request-id-notification"; }
//    @Override
//    public String getLogGroupName() { return "notification-service-log"; }
//    @Override
//    public String getLogStreamName() { return "test-stream"; }
//    @Override
//    public String getFunctionName() { return "notification-service-prod-handler"; }
//    @Override
//    public String getFunctionVersion() { return "1"; }
//    @Override
//    public String getInvokedFunctionArn() { return "arn:aws:lambda:us-east-1:365646127398:function:notification-service-prod-handler"; }
//    @Override
//    public CognitoIdentity getIdentity() { return null; }
//    @Override
//    public ClientContext getClientContext() { return null; }
//    @Override
//    public int getRemainingTimeInMillis() { return 300000; }
//    @Override
//    public int getMemoryLimitInMB() { return 512; }
//
//    @Override
//    public LambdaLogger getLogger() {
//        return new LambdaLogger() {
//            @Override
//            public void log(String message) { System.out.println(message); }
//            @Override
//            public void log(byte[] message) { System.out.println(new String(message)); }
//        };
//    }
//}