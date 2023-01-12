package com.lei.xia.jasper.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MovementTest {
  Movement movement = new Movement(null, "fromRef", "toRef");

  @Test
  void testGetUUID() {
    var actual = movement.getId();
    assertNotNull(actual);
  }

  @Test
  void testGetFromRef() {
    var actual = movement.getFromRef();
    assertThat(actual).isEqualTo("fromRef");
  }

  @Test
  void testGetToRef() {
    var actual = movement.getToRef();
    assertThat(actual).isEqualTo("toRef");
  }

  @Test
  void should_pretty_print_movement() throws JsonProcessingException {
    var movement = new Movement("1", "France", "UK");
    var objectMapper = new ObjectMapper();
    System.out.println(objectMapper.writeValueAsString(movement));
  }
}
