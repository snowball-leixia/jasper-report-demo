package com.lei.xia.jasper.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Movement {

  private String id;

  private String fromRef;

  private String toRef;

  public String getId() {
    if (id == null) {
      return UUID.randomUUID().toString();
    }
    return id.toString();
  }

  public String getFromRef() {
    return fromRef;
  }

  public String getToRef() {
    return toRef;
  }
}
