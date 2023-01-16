package com.lei.xia.jasper.mapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Test;

class MessageBuilderTest {

  @Test
  void should_build_optional_movement_data_to_time() {
    Optional<String> data = Optional.ofNullable(producer());
    var val =
        data.map(s -> s.toUpperCase(Locale.ENGLISH))
            .map(s -> s.replaceAll("_", " "))
            .orElse("EMPTY");

    System.out.println(val);
  }

  private String producer() {
    Collections.singletonList("s");
    boolean isSwitch = new Random().nextInt() % 2 > 0;
    if (isSwitch) {
      return "THIS is_on_washed_InPut";
    } else {
      return null;
    }
  }
}
