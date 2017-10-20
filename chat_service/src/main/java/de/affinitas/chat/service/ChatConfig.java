package de.affinitas.chat.service;

import org.springframework.stereotype.*
import org.springframework.beans.factory.annotation.*

public class ChatConfig {

    private String mqttRootTopic;      
    @Value("${mqttbroker.url}")
    private String mqttBrokerUrl;
//    @Value("${mqttbroker.user}")
    private String mqttUser;
//    @Value("${mqttbroker.password}")
    private String mqttPassword;

    public ChatConfig() {
        mqttRootTopic = "affinitas_chat/one_to_one";
        //mqttBrokerUrl = "tcp://localhost:61613";
        mqttUser = "admin";
        mqttPassword = "password";
    }

    public String getMqttRootTopic() {
        return mqttRootTopic;
    }

    public String getMqttBrokerUrl() {
        return mqttBrokerUrl;
    }

    public String getMqttUser() {
        return mqttUser;
    }

    public String getMqttPassword() {
        return mqttPassword;
    }
}
