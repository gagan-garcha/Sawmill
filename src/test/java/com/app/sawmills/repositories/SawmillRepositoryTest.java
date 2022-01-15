package com.app.sawmills.repositories;

import com.app.sawmills.models.Sawmill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SawmillRepositoryTest {

    @Autowired
    private SawmillRepository sawmillRepository;

    private Sawmill sawmill;


    @BeforeEach
    public  void setUp(){
        sawmill = Sawmill.builder().name("Test").city("Pune").country("India").build();
    }

    @Test
    public void saveAndFindByName(){
        sawmill = sawmillRepository.save(sawmill);
        Assertions.assertEquals(sawmill,sawmillRepository.findByName(sawmill.getName()));
    }
}
