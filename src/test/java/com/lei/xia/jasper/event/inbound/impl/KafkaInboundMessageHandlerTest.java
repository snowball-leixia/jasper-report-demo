package com.lei.xia.jasper.event.inbound.impl;

import com.lei.xia.jasper.model.Movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KafkaInboundMessageHandlerTest {
    private KafkaInboundMessageHandler kafkaInboundMessageHandler;

    @BeforeEach
    void beforeEach() {
        kafkaInboundMessageHandler = new KafkaInboundMessageHandler();
    }

    @Test
    void testHandle() {
        assertThatThrownBy(() ->
            kafkaInboundMessageHandler.handle(new Movement("id", "fromRef", "toRef"))
        ).isInstanceOf(UnsupportedOperationException.class).hasMessage("pending implementation");
    }
}
