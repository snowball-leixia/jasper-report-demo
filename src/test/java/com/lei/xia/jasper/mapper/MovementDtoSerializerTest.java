package com.lei.xia.jasper.mapper;


import org.apache.kafka.common.KafkaException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lei.xia.jasper.model.Movement;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovementDtoSerializerTest {
  private MovementDtoSerializer cut;
  private ObjectMapper objectMapper;

  @Test
  void should_return_null_with_null_object_input() {
    objectMapper = mock(ObjectMapper.class);
    cut = new MovementDtoSerializer(objectMapper);

    var actual = cut.serialize("test", null);
    assertThat(actual).isNull();
  }

  @Test
  void should_throw_kafka_exception() throws JsonProcessingException {
    objectMapper = mock(ObjectMapper.class);
    when(objectMapper.writeValueAsBytes(any(Movement.class))).thenThrow(JsonProcessingException.class);
    cut = new MovementDtoSerializer(objectMapper);

    assertThatThrownBy( () ->
        cut.serialize("test", new Movement("1", "2", "3")))
        .isInstanceOf(KafkaException.class);
  }

  @Test
  void should_serialize_movement_into_byte_array() {
    objectMapper = new ObjectMapper();
    cut = new MovementDtoSerializer(objectMapper);
    var actual = cut.serialize("test", new Movement("1", "2", "3"));
    assertThat(actual).isNotNull().hasSizeGreaterThan(0);
  }
}