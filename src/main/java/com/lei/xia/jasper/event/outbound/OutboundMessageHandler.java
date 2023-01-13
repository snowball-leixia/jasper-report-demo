package com.lei.xia.jasper.event.outbound;

@FunctionalInterface
public interface OutboundMessageHandler<T, S> {

  void publish(T message, S dest);
}
