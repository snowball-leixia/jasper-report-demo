package com.lei.xia.jasper.service.impl;

import com.lei.xia.jasper.config.JasperTemplateFactory;
import com.lei.xia.jasper.model.Movement;
import java.io.FileOutputStream;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PdfDocumentPrintServiceIntTest {
  private static PdfDocumentPrintService pdfDocumentPrintService;

  @BeforeAll
  static void beforeAll() {
    var config = new JasperTemplateFactory();
    var jasperReport = config.movementJasperReport("movement.jrxml");
    pdfDocumentPrintService = new PdfDocumentPrintService(jasperReport);
  }

  @Test
  void should_generate_a_sample_pdf() {
    var movement = new Movement(UUID.randomUUID().toString(), "France", "UK");
    byte[] actual = pdfDocumentPrintService.generate(movement);
    // printFile(actual);
    assertThat(actual).isNotNull();
  }

  private void printFile(byte[] data) throws Exception {
    var out = new FileOutputStream("out.pdf");
    out.write(data);
    out.close();
  }

}