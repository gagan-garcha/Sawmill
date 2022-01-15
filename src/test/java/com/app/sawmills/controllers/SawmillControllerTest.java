package com.app.sawmills.controllers;


import com.app.sawmills.models.Sawmill;
import com.app.sawmills.services.SawmillServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SawmillControllerTest {

    @Mock
    private SawmillServiceImpl sawmillService;

    @InjectMocks
    private  SawmillController sawmillController;

    private Sawmill sawmill,sawmill2;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        sawmill = Sawmill.builder().id(1L).name("test").city("pune").country("India").build();
        sawmill2 = Sawmill.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(sawmillController).build();
    }

    @Test
    public void postMappingForSawmill() throws Exception {
        when(sawmillService.insert(any())).thenReturn(sawmill);
        mockMvc.perform(post("/api/v1/sawmill").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(sawmill))).
                andExpect(status().isCreated());

        verify(sawmillService,times(1)).insert(any());
    }

    @Test
    public void invalidPostMappingForSawmill() throws Exception {
        mockMvc.perform(post("/api/v1/sawmill").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(sawmill2))).
                andExpect(MockMvcResultMatchers.status().isBadRequest()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void GetMappingOfProductShouldReturnRespectiveProducct() throws Exception {
        when(sawmillService.getSawmillByName(sawmill.getName())).thenReturn(sawmill);
        mockMvc.perform(get("/api/v1/sawmill?name=test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sawmill)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }



    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }




}
