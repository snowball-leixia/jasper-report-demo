package com.lei.xia.jasper.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lei.xia.jasper.event.inbound.InboundMessageHandler;
import com.lei.xia.jasper.event.outbound.OutboundMessageHandler;
import com.lei.xia.jasper.event.outbound.impl.KafkaOutboundMessageHandler;
import com.lei.xia.jasper.event.inbound.KafkaInboundMessageHandler;

import com.lei.xia.jasper.model.Movement;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;

@Configuration
@EnableKafka
public class KafkaConfiguration {

  @Bean
  public ConsumerFactory<String, Movement> consumerFactory(
      @Value("${spring.kafka.bootstrap-servers}") String broker,
      @Value("${spring.kafka.consumer.group-id}") String groupId) {
    return new DefaultKafkaConsumerFactory<>(Map.of(
      BOOTSTRAP_SERVERS_CONFIG, broker, GROUP_ID_CONFIG, groupId),
        new StringDeserializer(),
        new JsonDeserializer<>(Movement.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Movement> kafkaContainerFactory(
      @Autowired ConsumerFactory<String, Movement> consumerFactory) {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, Movement>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }

  @Bean
  public InboundMessageHandler kafkaInboundMessageHandler(@Autowired ObjectMapper objectMapper) {
    return new KafkaInboundMessageHandler(objectMapper);
   }
  @Bean
  public OutboundMessageHandler kafkaOutboundMessageHandler(@Autowired KafkaTemplate<String, Movement> kafkaTemplate) {
    return new KafkaOutboundMessageHandler(kafkaTemplate);
  }

}
