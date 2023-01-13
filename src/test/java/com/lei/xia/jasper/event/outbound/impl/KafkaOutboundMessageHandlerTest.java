package com.lei.xia.jasper.event.outbound.impl;

import com.lei.xia.jasper.model.Movement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class KafkaOutboundMessageHandlerTest {

  private static KafkaOutboundMessageHandler kafkaOutboundMessageHandler;
  private static KafkaTemplate kafkaTemplate;

  @BeforeAll
  static void beforeAll() {
    kafkaTemplate = mock(KafkaTemplate.class);
    kafkaOutboundMessageHandler = new KafkaOutboundMessageHandler(kafkaTemplate);
  }
  @Test
  void should_invoke_kafka_template_publish_message() {
    var movementCaptor = ArgumentCaptor.forClass(Movement.class);
    var topicCaptor = ArgumentCaptor.forClass(String.class);

    var movement = new Movement("1", "Start", "End");
    kafkaOutboundMessageHandler.publish(movement, "test-topic");

    verify(kafkaTemplate).send(topicCaptor.capture(), movementCaptor.capture());

    assertThat(topicCaptor.getValue()).isEqualTo("test-topic");
    assertThat(movementCaptor.getValue()).isEqualTo(movement);
  }

}