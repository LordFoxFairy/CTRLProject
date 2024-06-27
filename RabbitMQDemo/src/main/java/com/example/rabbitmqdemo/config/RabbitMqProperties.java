package com.example.rabbitmqdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author liuxy250
 */
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Data
public class RabbitMqProperties {
    private Map<String, RabbitProperties> instances;

    public Map<String, RabbitProperties> getInstances() {
        return instances;
    }

    public void setInstances(Map<String, RabbitProperties> instances) {
        this.instances = instances;
    }

    @Data
    public static class RabbitProperties {
        private String host;
        private int port;
        private String username;
        private String password;
        private String virtualHost;
        private String exchange;
        private String routingKey;
        private String type;
        private String queueName;
    }
}
