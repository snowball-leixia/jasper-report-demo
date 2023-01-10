package com.lei.xia.jasper.service.impl;

import com.lei.xia.jasper.exception.PdfGenerationException;
import com.lei.xia.jasper.model.Movement;
import com.lei.xia.jasper.service.DocumentPrintService;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfDocumentPrintService implements DocumentPrintService<Movement, byte[]> {

  private final JasperReport movementJasperReport;

  public byte[] generate(Movement source) {
    var dataSource = new JRBeanCollectionDataSource(List.of(source));
    try {
      var vars = new HashMap<String, Object>(1);
      vars.put("Author", "Lei Xia");
      var jasperPrinter = JasperFillManager.fillReport(movementJasperReport,
      vars, dataSource);
      return JasperExportManager.exportReportToPdf(jasperPrinter);
    } catch (JRException e) {
      throw new PdfGenerationException(String.format("Error: fail to generate report %s", 
      e.getMessage()));
    }
  }

}
