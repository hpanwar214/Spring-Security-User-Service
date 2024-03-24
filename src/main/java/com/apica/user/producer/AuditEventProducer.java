

package com.apica.user.producer;

import com.apica.user.domain.inbound.UserAuditEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditEventProducer {
    private final KafkaTemplate<String, UserAuditEvent> kafkaTemplate;
    @Value("${spring.kafka.topic.audit.name}")
    private String topic;
    public void sendAuditEvent(UserAuditEvent userAuditEvent) {
        kafkaTemplate.send(topic, userAuditEvent);
    }

}
