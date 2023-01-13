package com.lei.xia.jasper.event.outbound.impl;

import com.lei.xia.jasper.event.outbound.OutboundMessageHandler;
import com.lei.xia.jasper.model.Movement;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaOutboundMessageHandler implements OutboundMessageHandler<Movement, String> {

  private final KafkaTemplate<String, Movement> kafkaTemplate;
  public KafkaOutboundMessageHandler(KafkaTemplate<String, Movement> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void publish(Movement message, String topic) {
    kafkaTemplate.send(topic, message);
  }
}
