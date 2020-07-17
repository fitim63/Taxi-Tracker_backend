package com.ubt.integrationTest;


import com.ubt.app.App;
import com.ubt.model.Server;
import com.ubt.repository.ServerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConfiguration
public class ServerControllerIntegrationTest {

    private static MediaType CONTENT_TYPE =
            new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;
    @SuppressWarnings("rawtypes")
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    public void setConverters(HttpMessageConverter<?>[] converters) {
        mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);
        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void init() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        Server server1 = new Server();
        server1.setId(1);
        server1.setUsername("R9");
        server1.setFirstName("eric cartman");
        server1.setLastName("lastname  server1");
        server1.setPassword("password1");
        server1.setEmail("email@email.com");
        Server server2 = new Server();
        server2.setId(2);
        server2.setUsername("R10");
        server2.setFirstName("illmatic");
        server2.setLastName("friday");
        server2.setPassword("password2");
        server2.setEmail("email2@email.com");
        Server server3 = new Server();
        server3.setId(3);
        server3.setUsername("username 3");
        server3.setFirstName("firstname3");
        server3.setLastName("llalastname3");
        server3.setPassword("word");
        server3.setEmail("email2@email.com");
        serverRepository.save(server1);
        serverRepository.save(server2);
        serverRepository.save(server3);
    }


    @Test
    public void getNonExistingEndpoint() throws Exception {
        mockMvc.perform(get("/nonexistingendpoint")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllServersAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/servers")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*.id").exists());
    }
}
