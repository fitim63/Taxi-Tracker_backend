package com.ubt.app.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubt.app.App;
import com.ubt.model.Server;
import com.ubt.repository.ServerRepository;
import com.ubt.unitTest.ServerService;
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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ServerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServerService serverService;

    @Autowired
    private ServerRepository serverRepository;

//    @Test
//    void add_UsernameAndFirstname_ShouldReturnValidationErrors() throws Exception {
//
//        String username = createStringWithLength(603);
//        String firstname = createStringWithLength(602);
//
//        Server server = new Server();
//        server.setId(1);
//        server.setUsername(username);
//        server.setFirstName(firstname);
//        server.setLastName("test lastname monitoruesi");
//        server.setPassword("password");
//        server.setEmail("test@gmail.com");
//
//
//        mockMvc.perform(post("/createServer").contentType(MediaType.APPLICATION_JSON)
//                .content(convertObjectToJsonBytes(server)))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.fieldErrors", hasSize(2)))
//                .andExpect(jsonPath("$.fieldErrors[*].path", containsInAnyOrder("username", "firstname")))
//                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder(
//                        "The maximum length of the description is 500 characters.",
//                        "The maximum length of the title is 100 characters."
//                )));
//    }


    @Test
    void listAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080/servers";
        try {
            URI uri = new URI(baseUrl);
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

            Assert.assertEquals(200, result.getStatusCodeValue());
            Assert.assertTrue(result.getBody().contains("monitor2"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUser() throws Exception {

        Server mockServer = new Server();
        mockServer.setId(1);
        mockServer.setUsername("test monitoruesi");
        mockServer.setFirstName("monitoruesi lastname");
        mockServer.setLastName("test lastname monitoruesi");
        mockServer.setPassword("password");
        mockServer.setEmail("test@gmail.com");

        Mockito.when(serverService.getById(Mockito.anyInt())).thenReturn(mockServer);

        String URI = "http://localhost:8080/servers/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(mockServer);
        String outputInJson = result.getResponse().getContentAsString();
        Assert.assertEquals(outputInJson, expectedJson);
    }


    @Test
    void createUser() {
        Server server = new Server();
        server.setId(1);
        server.setUsername("test monitoruesi");
        server.setFirstName("monitoruesi lastname");
        server.setLastName("test lastname monitoruesi");
        server.setPassword("password");
        server.setEmail("test@gmail.com");

        serverService.save(server);
    }
//
//    @Test
//    void updateUser() {
//        String username = "monitor2";
//        Server server = new Server();
//
//        server.setId(2);
//        serverRepository.save(server);
//
//        Server updatedServer = serverRepository.findByUsername(username);
//
//        Assert.assertEquals("updated successful",updatedServer.getUsername(), username);
//
//    }

    @Test
    void deleteUser() {
        Integer id = 4;

        boolean existedBeforeDelete = serverRepository.findById(id).isPresent();
        serverRepository.deleteById(id);

        boolean doesntExistAfterDelete = serverRepository.findById(id).isPresent();

        Assert.assertTrue("existed before delete: ", existedBeforeDelete);
        Assert.assertFalse("doesnt exist after delete: ", doesntExistAfterDelete);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {
            builder.append("a");
        }

        return builder.toString();
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}