package com.gymservice.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gymservice.security.dto.AuthenticationRequest;
import com.gymservice.web.mappers.UserMapperImpl;
import com.gymservice.web.userAPI.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapperImpl userMapper;

    @Autowired
    private ObjectMapper objectMapper;


    private String bearerToken;

    @BeforeEach
    public void setup() throws Exception {
        // TODO authentication to TEST_DB with ADMIN USER and / or ROLE USER
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("ADMIN");
        authenticationRequest.setPassword("MyP@$$w0rd");

        // authenticate
        ResultActions response = mockMvc.perform(post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authenticationRequest))
        );

        bearerToken = response.andReturn().getResponse().getContentAsString();
    }


    //TODO duplicate email check


    @Test
    public void givenNoToken_whenRequest_thenReturn403() throws Exception {

        // when
        ResultActions response = mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        response.andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void givenUserId_whenGetUsers_thenReturnUsers() throws Exception {
        // when
        ResultActions response = mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + bearerToken)
        );

        // then
        response.andDo(print())
                .andExpect(status().is2xxSuccessful());
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(jsonPath("$[0].username", is("ADMIN") ));
    }
}
