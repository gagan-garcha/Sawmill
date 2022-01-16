package com.app.sawmills.repositories;

import com.app.sawmills.models.Sawmill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(sawmill,sawmillRepository.findByNameIgnoreCase(sawmill.getName()));
    }

    @Test
    public void findById(){
        Sawmill savedSawmill = sawmillRepository.save(sawmill);
        assertEquals(savedSawmill.getId(),sawmillRepository.findById(savedSawmill.getId()).get().getId());
    }

    @Test
    public void findAll(){
        sawmillRepository.save(sawmill);
       List<Sawmill> sawmills = (List<Sawmill>) sawmillRepository.findAll();

       assertEquals(1,sawmills.size());
    }

    @Test
    public void filterByName(){
        Sawmill sawmill1 = Sawmill.builder().name("Test2").city("Pune").country("India").build();
        Sawmill sawmill2 = Sawmill.builder().name("Test3").city("Pune").country("India").build();
        sawmillRepository.save(sawmill);
        sawmillRepository.save(sawmill1);
        sawmillRepository.save(sawmill2);

        List<Sawmill> filteredSawmills = sawmillRepository.findByNameContainingIgnoreCase("t");

        assertEquals(3,filteredSawmills.size());

    }


}
