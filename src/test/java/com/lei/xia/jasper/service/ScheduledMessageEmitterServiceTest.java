package com.lei.xia.jasper.service;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import com.lei.xia.jasper.event.outbound.impl.KafkaOutboundMessageHandler;
import com.lei.xia.jasper.model.Movement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScheduledMessageEmitterServiceTest {
  private static ScheduledMessageEmitterService emitterService;
  private static KafkaOutboundMessageHandler outboundMessageHandler;
  private static Faker faker;
  private static Address address;
  private static IdNumber idNumber;
  @BeforeAll
  static void beforeAll() {
    outboundMessageHandler = mock(KafkaOutboundMessageHandler.class);
    faker = mock(Faker.class);
    address = mock(Address.class);
    idNumber = mock(IdNumber.class);
    when(faker.address()).thenReturn(address);
    when(address.country()).thenReturn("FRA").thenReturn("UK");
    when(faker.idNumber()).thenReturn(idNumber);
    when(idNumber.toString()).thenReturn("1");
    emitterService = new ScheduledMessageEmitterService(outboundMessageHandler, faker);
  }
  @Test
  void should_invoke_outbound_message_handler_emit_movement_message() {
    emitterService.emit();

    var movementArgumentCaptor  = ArgumentCaptor.forClass(Movement.class);
    var strCaptor = ArgumentCaptor.forClass(String.class);

    verify(outboundMessageHandler).publish(movementArgumentCaptor.capture(), strCaptor.capture());

    var movement = movementArgumentCaptor.getValue();
    assertThat(movement.getId()).isEqualTo("1");
    assertThat(movement.getFromRef()).isNotNull().isEqualTo("FRA");
    assertThat(movement.getToRef()).isNotNull().isEqualTo("UK");
    assertThat(strCaptor.getValue()).isEqualTo("foobar");
  }


}