package com.example.rabbitmqdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxy250
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RabbitMqProperties.class)
@SuppressWarnings({"java:S1452"})
public class RabbitMqConfiguration {

    private final RabbitMqProperties rabbitMqProperties;
    private final ApplicationContext applicationContext;

    @Autowired
    public RabbitMqConfiguration(RabbitMqProperties rabbitMqProperties, ApplicationContext applicationContext) {
        this.rabbitMqProperties = rabbitMqProperties;
        this.applicationContext = applicationContext;
    }

    @Bean(value = "connectionFactories")
    public Map<String, ConnectionFactory> connectionFactories() {
        Map<String, ConnectionFactory> factories = new HashMap<>();
        for (Map.Entry<String, RabbitMqProperties.RabbitProperties> entry : rabbitMqProperties.getInstances().entrySet()) {
            CachingConnectionFactory factory = new CachingConnectionFactory();
            factory.setHost(entry.getValue().getHost());
            factory.setPort(entry.getValue().getPort());
            factory.setUsername(entry.getValue().getUsername());
            factory.setPassword(entry.getValue().getPassword());
            factory.setVirtualHost(entry.getValue().getVirtualHost());
            factories.put(entry.getKey(), factory);
        }
        return factories;
    }

    @Bean("rabbitAdmins")
    public Map<String, RabbitAdmin> rabbitAdmins(@Qualifier("connectionFactories") Map<String, ConnectionFactory> connectionFactories) {
        Map<String, RabbitAdmin> admins = new HashMap<>();
        for (Map.Entry<String, ConnectionFactory> entry : connectionFactories.entrySet()) {
            RabbitAdmin rabbitAdmin = new RabbitAdmin(entry.getValue());
            rabbitAdmin.afterPropertiesSet(); // 确保 rabbitAdmin 已初始化
            admins.put(entry.getKey(), rabbitAdmin);
            log.info("Created RabbitAdmin for instance: {}", entry.getKey());
        }
        return admins;
    }

    @Bean("exchanges")
    public Map<String, Exchange> exchanges() {
        Map<String, Exchange> exchanges = new HashMap<>();
        for (Map.Entry<String, RabbitMqProperties.RabbitProperties> entry : rabbitMqProperties.getInstances().entrySet()) {
            String instanceName = entry.getKey();
            String exchangeType = entry.getValue().getType(); // 获取配置中的交换机类型
            Exchange exchange;
            switch (exchangeType) {
                case "direct":
                    exchange = new DirectExchange(entry.getValue().getExchange());
                    break;
                case "fanout":
                    exchange = new FanoutExchange(entry.getValue().getExchange());
                    break;
                case "topic":
                default:
                    exchange = new TopicExchange(entry.getValue().getExchange());
                    break;
            }
            exchanges.put(instanceName, exchange);
            log.info("Created Exchange '{}' for instance '{}'", exchange.getClass().getSimpleName(), instanceName);
        }
        return exchanges;
    }

    @Bean
    public Map<String, List<Binding>> bindings(@Qualifier("rabbitAdmins") Map<String, RabbitAdmin> rabbitAdmins, @Qualifier("exchanges") Map<String, Exchange> exchanges) {
        Map<String, List<Binding>> bindingsMap = new HashMap<>();
        for (Map.Entry<String, RabbitMqProperties.RabbitProperties> entry : rabbitMqProperties.getInstances().entrySet()) {
            String instanceName = entry.getKey();
            RabbitAdmin rabbitAdmin = rabbitAdmins.get(instanceName);
            Exchange exchange = exchanges.get(instanceName);
            if (rabbitAdmin != null && exchange != null) {
                bindingsMap.put(instanceName, Collections.singletonList(
                        createBinding(rabbitAdmin, createQueue(entry.getValue().getQueueName()), exchange, entry.getValue().getRoutingKey())
                ));
            } else {
                log.error("RabbitAdmin or Exchange is null for instance: {}", instanceName);
            }
        }
        return bindingsMap;
    }

    private Queue createQueue(String name) {
        return new Queue(name, true, false, false);
    }

    private Binding createBinding(RabbitAdmin rabbitAdmin, Queue queue, Exchange exchange, String routingKey) {
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean("rabbitTemplates")
    public Map<String, RabbitTemplate> rabbitTemplates(@Qualifier("connectionFactories")Map<String, ConnectionFactory> connectionFactories) {
        Map<String, RabbitTemplate> templates = new HashMap<>();
        for (Map.Entry<String, ConnectionFactory> entry : connectionFactories.entrySet()) {
            templates.put(entry.getKey(), new RabbitTemplate(entry.getValue()));
        }
        return templates;
    }

    @Bean("rabbitListenerContainerFactories")
    public Map<String, RabbitListenerContainerFactory<?>> rabbitListenerContainerFactories(@Qualifier("connectionFactories") Map<String, ConnectionFactory> connectionFactories) {
        Map<String, RabbitListenerContainerFactory<?>> factories = new HashMap<>();
        for (Map.Entry<String, ConnectionFactory> entry : connectionFactories.entrySet()) {
            String factoryBeanName = entry.getKey() + "RabbitListenerContainerFactory";
            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
            factory.setConnectionFactory(entry.getValue());
            factories.put(factoryBeanName, factory);
            registerBean(factoryBeanName, factory);
            log.info("Created RabbitListenerContainerFactory: {} -> {}", factoryBeanName, factory);
        }
        return factories;
    }

    private void registerBean(String beanName, RabbitListenerContainerFactory<?> factory) {
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
        beanFactory.registerSingleton(beanName, factory);
    }
}
