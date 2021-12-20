package com.gini.iordache.controllers;


import com.gini.iordache.ServiceAutoApplication;
import com.gini.iordache.config.FieldsValidationConfig;
import com.gini.iordache.config.PdfGeneratorConfig;
import com.gini.iordache.config.SecurityConfig;
import com.gini.iordache.controllers.auto.PartController;
import com.gini.iordache.services.interfaces.PartService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.Assert;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = PartController.class)
//@ContextConfiguration(classes = ServiceAutoApplication.class)


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = ServiceAutoApplication.class)

public class TEst {

    @MockBean
    public PartService partService;

    @Autowired
    public PartController partController;



    @Test
    @DisplayName("check been injection")
    public void checkBean(){
        Assert.notNull(partController, "Nu mege bossss -------------->");
    }

}
