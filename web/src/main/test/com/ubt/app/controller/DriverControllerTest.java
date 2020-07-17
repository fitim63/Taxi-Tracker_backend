package com.ubt.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubt.app.App;
import com.ubt.model.Driver;
import com.ubt.repository.DriverRepository;
import com.ubt.unitTest.DriverService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DriverControllerTest extends Mockito {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    @Autowired
    private DriverRepository driverRepository;

    @Test
    void listAllUsers() {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8080/drivers";
        try {
            URI uri = new URI(baseUrl);
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

            Assert.assertEquals(200, result.getStatusCodeValue());
            Assert.assertTrue(result.getBody().contains("driver1"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_APIWithOAuth2Authentication_ShouldBeGivenAccess() {

        RestAssured.given().
                auth().
                preemptive().
                oauth2("eyJhbGciOiJIUzUxMiJ9." +
                        "eyJzdWIiOiJtb25pdG9yMyIsImF1dGhvcm" +
                        "l0aWVzIjpbXSwiaWF0IjoxNTkzMjk1MjAwfQ" +
                        ".MT-89e2XC3DjowVOTCzXjXehq-" +
                        "FA47EzaHmoJVeMfT1oxP22mwVmLZlnEj-" +
                        "TJw4P1r1uzRHzz3SzHXFBt9Q4TQ").
                when().
                get("http://localhost:8080/drivers").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_ResponseHeaderData_ShouldBeCorrect() {

        RestAssured.given().
                when().
                get("http://localhost:8080/drivers").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                header("X-Content-Type-Options",equalTo("nosniff"));
    }

    @Test
    void getUser() throws Exception {
        Driver mockDriver = new Driver();
        mockDriver.setId(2);
        mockDriver.setFirstName("driver lamont");
        mockDriver.setLastName("coleman driver");
        mockDriver.setUsername("username driver");
        mockDriver.setDateOfBirth(1995);
        mockDriver.setAge(25);
        mockDriver.setPassword("password2222");

        Mockito.when(driverService.getById(Mockito.anyInt())).thenReturn(mockDriver);

        String URI = "http://localhost:8080/drivers/2";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(mockDriver);
        String outputInJson = result.getResponse().getContentAsString();
        Assert.assertEquals(outputInJson, expectedJson);
    }

    @Test
    void createUser() {

                Driver driver = new Driver();
        driver.setId(2);
        driver.setFirstName("driver lamont");
        driver.setLastName("coleman driver");
        driver.setUsername("username driver");
        driver.setDateOfBirth(1995);
        driver.setAge(25);
        driver.setPassword("passswordd233");

        driverService.save(driver);
    }

    @Test
    void deleteUser() {
        Integer id = 4;

        boolean existedBeforeDelete = driverRepository.findById(id).isPresent();
        driverRepository.deleteById(id);

        boolean doesntExistAfterDelete = driverRepository.findById(id).isPresent();

        Assert.assertTrue("driver existed before delete: ", existedBeforeDelete);
        Assert.assertFalse("driver doesnt exist after delete: ", doesntExistAfterDelete);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}