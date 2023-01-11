package com.lei.xia.jasper.service.impl;

import com.lei.xia.jasper.exception.PdfGenerationException;
import com.lei.xia.jasper.model.Movement;
import com.lei.xia.jasper.service.DocumentPrintService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

@Service
public class PdfDocumentPrintService implements DocumentPrintService<Movement, byte[]> {


  private final JasperReport movementJasperReport;
  private Map<String, Object> variables;

  public PdfDocumentPrintService(JasperReport movementJasperReport, Map<String, Object> variables) {
    this.movementJasperReport = movementJasperReport;
    if (variables == null) {
      this.variables = new HashMap<>();
    }
  }
  public byte[] generate(Movement source) {
    var dataSource = new JRBeanCollectionDataSource(List.of(source));
    try {
      var jasperPrinter = JasperFillManager.fillReport(movementJasperReport,
          this.variables, dataSource);
      return JasperExportManager.exportReportToPdf(jasperPrinter);
    } catch (JRException e) {
      throw new PdfGenerationException(String.format("Error: fail to generate report %s",
          e.getMessage()));
    }
  }

}
