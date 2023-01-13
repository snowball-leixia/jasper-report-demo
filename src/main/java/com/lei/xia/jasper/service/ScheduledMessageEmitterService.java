package com.lei.xia.jasper.service;

import com.github.javafaker.Faker;
import com.lei.xia.jasper.event.outbound.OutboundMessageHandler;
import com.lei.xia.jasper.event.outbound.impl.KafkaOutboundMessageHandler;
import com.lei.xia.jasper.model.Movement;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledMessageEmitterService {

  private final OutboundMessageHandler<Movement, String> outboundMessageHandler;
  private final Faker javaFaker;
  public ScheduledMessageEmitterService(KafkaOutboundMessageHandler outboundMessageHandler, Faker faker) {
    this.outboundMessageHandler = outboundMessageHandler;
    this.javaFaker = faker;
  }

  @Scheduled(initialDelay = 1L, fixedRate = 1L)
  public void emit() {
    outboundMessageHandler.publish(
        new Movement(this.javaFaker.idNumber().toString(),
        this.javaFaker.address().country(),
        this.javaFaker.address().country()
        ), "foobar");
  }
}
