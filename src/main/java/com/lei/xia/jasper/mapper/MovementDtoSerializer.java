package com.lei.xia.jasper.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lei.xia.jasper.model.Movement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
@RequiredArgsConstructor
public class MovementDtoSerializer implements Serializer<Movement> {

  private final ObjectMapper objectMapper;

  @Override
  public byte[] serialize(String topic, Movement movement) {
    if (movement == null) {
      log.warn("Null Movement object received nothing to serialize");
      return null;
    }
    try {
      return this.objectMapper.writeValueAsBytes(movement);
    } catch (JsonProcessingException e) {
      log.error("Movement can not be serialize into valid json {}", e.getMessage());
      throw new KafkaException(e.getMessage());
    }
  }
}
