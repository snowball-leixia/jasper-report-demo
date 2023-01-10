package com.lei.xia.jasper.config;

import com.lei.xia.jasper.exception.SetupException;
import net.sf.jasperreports.engine.JasperReport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JasperTemplateFactoryTest {

    private static JasperTemplateFactory jasperTemplateFactory;

    @BeforeAll
    static void beforeAll() {
        jasperTemplateFactory = new JasperTemplateFactory();
    }

    @Test
    void should_create_jasper_report() {    
        var actual = jasperTemplateFactory.movementJasperReport("movement.jrxml");
        assertThat(actual).isInstanceOf(JasperReport.class);
    }

    @Test
    void should_throw_setupUp_exeption_with_bad_file_name() {
        assertThrows(SetupException.class, () -> {
            jasperTemplateFactory.movementJasperReport("bad.file");
        });
    }
}
