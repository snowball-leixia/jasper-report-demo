package com.lei.xia.jasper.event.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lei.xia.jasper.model.Movement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class KafkaInboundMessageHandler implements InboundMessageHandler<String> {

  private final ObjectMapper objectMapper;

  public KafkaInboundMessageHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @KafkaListener(topics = "${application.kafka.topic:foobar}")
  @Override
  public void handle(String message) {
    try {
      Movement movement = this.objectMapper.readValue(message, Movement.class);
    } catch (JsonProcessingException e) {
      log.error("Failed to de-serialize inbound message to {}", e.getMessage());
      throw new RuntimeException(e);
    }
    log.info("received message from kafka {}", message);
    throw new UnsupportedOperationException("pending implementation");
  }

}
