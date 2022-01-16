package com.app.sawmills;

import com.app.sawmills.models.Sawmill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SawmillsApplication.class)
public class EndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldInsertSawmill() throws JsonProcessingException {

        Sawmill sawmill = Sawmill.builder().name("test").city("pune").country("India").build();

        HttpEntity<String> entity = getStringHttpEntity(sawmill);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/sawmill" ,entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void givenSawmillNameShouldReturnSawmill(){
        String sawmillName = "test";

        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/sawmill?name=" + sawmillName, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private HttpEntity<String> getStringHttpEntity(Object object) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonMeterData = mapper.writeValueAsString(object);
        return (HttpEntity<String>) new HttpEntity(jsonMeterData, headers);
    }

}
