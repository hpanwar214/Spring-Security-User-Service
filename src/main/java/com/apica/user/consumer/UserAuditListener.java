

package com.apica.user.consumer;

import com.apica.user.domain.inbound.UserAuditEvent;
import com.apica.user.domain.mapper.UserMapper;
import com.apica.user.domain.repository.UserAuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.KafkaMessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(
        topics = "${spring.kafka.topic.audit.name}",
        groupId = "${spring.kafka.topic.audit.group}")
@Slf4j
public class UserAuditListener {
    private final UserAuditRepository userAuditRepository;
    private static final UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    @KafkaHandler
    public void consumeUserAuditEvent(
            @Payload UserAuditEvent userAuditEvent,
            @Headers KafkaMessageHeaders headers) {
        log.info("Consuming user audit event: {}", userAuditEvent);
        consumeUserAuditEvent(userAuditEvent);

    }
    private void consumeUserAuditEvent(UserAuditEvent userAuditEvent) {
        userAuditRepository.save(MAPPER.toUserAuditEntity(userAuditEvent));
    }
}
