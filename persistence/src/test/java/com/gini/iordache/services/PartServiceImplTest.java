package com.gini.iordache.services;

import com.gini.iordache.dao.iterfaces.PartDao;
import com.gini.iordache.services.impl.auto.PartServiceImpl;
import com.gini.iordache.services.interfaces.PartService;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)

@SpringBootTest(properties = "spring.main.lazy-initialization=true")
//@SpringBootConfiguration
//@EnableAutoConfiguration
@SpringBootConfiguration
public class PartServiceImplTest{

//    @MockBean
//    private PartDao partDao;
//
//    @MockBean
//    private EntityManager entityManager;

    @Autowired
    private PartService partService;



    @Test
    @DisplayName("verify if the PartService bean is injected from Spring Context")
    public void checkPartServiceInjection(){
        Assert.notNull(partService, "No bean of PartService injected");
    }

}
