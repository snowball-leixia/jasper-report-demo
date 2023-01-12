package com.lei.xia.jasper.event.inbound.impl;

import com.lei.xia.jasper.event.inbound.InboundMessageHandler;
import com.lei.xia.jasper.model.Movement;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaInboundMessageHandler implements InboundMessageHandler<Movement> {

  @KafkaListener(topics = "test")
  @Override
  public void handle(Movement message) {
    throw new UnsupportedOperationException("pending implementation");
  }

}
