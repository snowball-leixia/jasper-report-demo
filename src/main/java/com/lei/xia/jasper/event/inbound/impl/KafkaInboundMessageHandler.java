package com.lei.xia.jasper.event.inbound.impl;

import com.lei.xia.jasper.event.inbound.InboundMessageHandler;
import com.lei.xia.jasper.model.Movement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class KafkaInboundMessageHandler implements InboundMessageHandler<Movement> {

  @KafkaListener(topics = "foobar")
  @Override
  public void handle(Movement message) {
    log.info("received message from kafka {}", message.toString());
    throw new UnsupportedOperationException("pending implementation");
  }

}
