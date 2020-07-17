package com.ubt.integrationTest;


import com.ubt.app.App;
import com.ubt.model.Driver;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DriverControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void saveDriverTest(){
        ResponseEntity<Driver> responseEntity = testRestTemplate.getForEntity("/servers/1", Driver.class);

        Driver driver = new Driver();
        driver.setId(1);

        HttpEntity<Driver> request = new HttpEntity<>(driver);

        ResponseEntity<Driver> response = testRestTemplate
                .postForEntity("/createDriver", request, Driver.class);

        assertNotNull(response.getBody().getId());
    }

}
