package com.vlz.userservice.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value("${spring.kafka.topic.partitions}")
    private int partitions;
    @Value("${spring.kafka.topic.replicationFactor}")
    private short replicationFactor;

    @Value("${spring.kafka.topic.names.user-add-request-topic}")
    private String userAddRequestTopicName;
    @Value("${spring.kafka.topic.names.user-add-reply-topic}")
    private String userAddReplyTopicName;
    @Value("${spring.kafka.topic.names.user-find-by-username-request-topic}")
    private String userFindByUsernameRequestTopicName;
    @Value("${spring.kafka.topic.names.user-find-by-username-reply-topic}")
    private String userFindByUsernameReplyTopicName;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic userAddRequestTopic() {
        return new NewTopic(userAddRequestTopicName, partitions, replicationFactor);
    }

    @Bean
    public NewTopic userAddReplyTopic() {
        return new NewTopic(userAddReplyTopicName, partitions, replicationFactor);
    }

    @Bean
    public NewTopic userFindByUsernameRequestTopic() {
        return new NewTopic(userFindByUsernameRequestTopicName, partitions, replicationFactor);
    }

    @Bean
    public NewTopic userFindByUsernameReplyTopicName() {
        return new NewTopic(userFindByUsernameReplyTopicName, partitions, replicationFactor);
    }
}