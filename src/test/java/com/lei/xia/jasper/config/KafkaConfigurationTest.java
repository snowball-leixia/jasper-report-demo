package com.lei.xia.jasper.config;

import com.lei.xia.jasper.event.inbound.impl.KafkaInboundMessageHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class KafkaConfigurationTest {


  private static KafkaConfiguration kafkaConfiguration;

  @BeforeAll
  static void beforeAll() {
    kafkaConfiguration = new KafkaConfiguration();
  }


  @Test
  void should_create_consumer_factory_bean() {
    var actual = kafkaConfiguration.consumerFactory("test-broker", "test-group-id");
    assertThat(actual).isNotNull().isExactlyInstanceOf(DefaultKafkaConsumerFactory.class);
  }


  @Test
  void should_create_kafka_container_factory_bean() {
   var consumerFactory = mock(ConsumerFactory.class);
   var actual =  kafkaConfiguration.kafkaContainerFactory(consumerFactory);
   assertThat(actual).isNotNull().isExactlyInstanceOf(ConcurrentKafkaListenerContainerFactory.class);
  }

  @Test
  void should_create_kafka_inbound_message_handler() {
    var actual = kafkaConfiguration.kafkaInboundMessageHandler();
    assertThat(actual).isNotNull().isExactlyInstanceOf(KafkaInboundMessageHandler.class);
  }
}
