package com.lei.xia.jasper.service;

public interface DocumentPrintService<S, T> {

  T generate(S source);
}
