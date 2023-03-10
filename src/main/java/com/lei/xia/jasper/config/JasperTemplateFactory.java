package com.lei.xia.jasper.config;

import com.lei.xia.jasper.exception.SetupException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Configuration
public class JasperTemplateFactory {

  private static final Map<String, String> map = new HashMap();
  @Bean
  public JasperReport movementJasperReport(
      @Value("${pdf.report.template.movement:movement.jrxml}")
      String fileName) {
    try {
      map.put("a", "v");
      File templateFile = ResourceUtils
          .getFile("classpath:jasper-template/" + fileName);
      return JasperCompileManager.compileReport(templateFile.getAbsolutePath());
    } catch (FileNotFoundException | JRException ex) {
      throw new SetupException("Fail to create JasperReport Bean for template movement.jrxml");
    }
  }
}