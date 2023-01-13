package com.lei.xia.jasper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JasperApplication {

  public static void main(String[] args) {
    SpringApplication.run(JasperApplication.class, args);
  }

}
