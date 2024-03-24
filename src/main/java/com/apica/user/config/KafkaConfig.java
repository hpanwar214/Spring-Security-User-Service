

package com.apica.user.config;

import com.apica.user.domain.inbound.UserAuditEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final KafkaProperties kafkaProperties;
    private static final String KEYSERIALIZER = "key.serializer";
    private static final String INTERCEPTORCLASSES = "interceptor.classes";
    private static final String VALUESERIALIZER = "value.serializer";
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<String, UserAuditEvent> kafkaTemplate() {
        Map<String, Object> prop = getStringObjectMap();
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(prop));
    }
    private Map<String, Object> getStringObjectMap() {
        return new HashMap<>(this.kafkaProperties.buildProducerProperties());
    }
}
