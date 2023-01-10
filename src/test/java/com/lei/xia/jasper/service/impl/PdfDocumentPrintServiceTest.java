package com.lei.xia.jasper.service.impl;

import com.lei.xia.jasper.model.Movement;
import java.util.UUID;
import net.sf.jasperreports.engine.JasperReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PdfDocumentPrintServiceTest {

    @InjectMocks
    private PdfDocumentPrintService pdfDocumentPrintService;
    @Mock
    private JasperReport jasperReportMock;

    @Test
    void should_generate_empty_byte_array_from_movement() {
        var movement = new Movement(UUID.randomUUID().toString(), "France", "UK");
        var actual = pdfDocumentPrintService.generate(movement);
        assertEquals(1, actual.length);
    }
}