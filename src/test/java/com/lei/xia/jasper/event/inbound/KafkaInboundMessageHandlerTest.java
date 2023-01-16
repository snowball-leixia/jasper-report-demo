package com.lei.xia.jasper.event.inbound;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lei.xia.jasper.model.Movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KafkaInboundMessageHandlerTest {

  private KafkaInboundMessageHandler kafkaInboundMessageHandler;
  private ObjectMapper objectMapper;

  @BeforeEach
  void beforeEach() {
    objectMapper = mock(ObjectMapper.class);
    kafkaInboundMessageHandler = new KafkaInboundMessageHandler(objectMapper);
  }

  @Test
  void testHandle() {
    assertThatThrownBy(() ->
        kafkaInboundMessageHandler.handle("{}")
    ).isInstanceOf(UnsupportedOperationException.class).hasMessage("pending implementation");
  }
}
