package com.lei.xia.jasper.model;

import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    assertEquals(Optional.of("fromRef"), actual);
  }

  @Test
  void testGetToRef() {
    var actual = movement.getToRef();
    assertEquals(Optional.of("toRef"), actual);
  }
}
