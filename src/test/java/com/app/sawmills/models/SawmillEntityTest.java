package com.app.sawmills.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SawmillEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private Sawmill sawmill;


    @BeforeEach
    public void setUp(){
        sawmill = Sawmill.builder().name("Test").city("Pune").country("India").build();
    }

    @Test
    public void saveSawmillEntity(){
        Sawmill savedSawmill = this.entityManager.persistAndFlush(sawmill);
        assertEquals(1,savedSawmill.getId());
        assertNotNull(savedSawmill.getCreated_at());
        assertNotNull(savedSawmill.getUpdated_at());
    }
}
