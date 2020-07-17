package com.ubt.service.service;

import com.ubt.app.App;
import com.ubt.model.Server;
import com.ubt.repository.ServerRepository;
import com.ubt.service.ServerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = App.class)
class ServerServiceTest {

    @Autowired
    private ServerRepository serverRepository;

    @Mock
    private ServerRepository mockServerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ServerService serverService;

    @Test
    void getByUsernameHttpStatus400() throws Exception {
    ServerService mock = Mockito.mock(ServerService.class);
    when(mock.getByUsername("monitor2")).thenThrow(new ArrayIndexOutOfBoundsException("asd"));
    mockMvc.perform(get("/servers/username"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void getByUsernameNotExist(){
        String name = "not existing user!";
        Server s = serverRepository.findByUsername(name);

        Assert.assertNull(s);
    }
}