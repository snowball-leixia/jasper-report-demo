package com.lei.xia.jasper.event.inbound;


@FunctionalInterface
public interface InboundMessageHandler<T> {

  void handle(T message);
}
