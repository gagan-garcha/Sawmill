package com.app.sawmills.services;

import com.app.sawmills.models.Sawmill;
import com.app.sawmills.repositories.SawmillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SawmillServiceImplTest {

    @Mock
    private SawmillRepository sawmillRepository;

    @InjectMocks
    @Autowired
    private SawmillServiceImpl sawmillService;


    private Sawmill sawmill1,sawmill2,sawmill3;

    private Map<String,Object> changes;

    @BeforeEach
    private void setUp(){
        sawmill1 = Sawmill.builder().id(1L).name("Test1").city("Pune").country("India").build();
        sawmill2 = Sawmill.builder().id(2L).name("Test2").city("Pune").country("India").build();
        sawmill3 = Sawmill.builder().id(1L).name("Test3").city("Pune").country("India").build();
        changes = Map.of("name","Test3");
    }


    @Test
    void givenSawmillToAddShouldReturnAddedSawmill(){
        when(sawmillRepository.save(any())).thenReturn(sawmill1);
        sawmillService.insert(sawmill1);
        verify(sawmillRepository,times(1)).save(any());
    }

    @Test
    void givenSawmillNameShouldReturnSawmill(){
        when(sawmillRepository.findByName(sawmill1.getName())).thenReturn(sawmill1);
        when(sawmillRepository.findByName(sawmill2.getName())).thenReturn(sawmill2);
        assertThat(sawmillService.getSawmillByName(sawmill1.getName())).isEqualTo(sawmill1);
        assertThat(sawmillService.getSawmillByName(sawmill2.getName())).isEqualTo(sawmill2);
    }

    @Test
    void givenNewValuesShouldBeUpdated(){

        when(sawmillRepository.findById(sawmill1.getId())).thenReturn(Optional.ofNullable(sawmill1));
        when(sawmillRepository.save(any())).thenReturn(sawmill3);
        Sawmill updatedSawmill = sawmillService.updateSawmill(sawmill1.getId(),changes);
        assertThat(updatedSawmill).isEqualTo(sawmill3);
    }

    @Test
    void givenFilerNameReturnListOfSawmills(){
        when(sawmillRepository.findByNameContainingIgnoreCase(any())).thenReturn(List.of(sawmill1));
        List<Sawmill> sawmills = sawmillService.getSawmills("t");
        verify(sawmillRepository,times(1)).findByNameContainingIgnoreCase(any());
    }



}
