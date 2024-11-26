package com.bulatmain.authorization.infrastructure.controller;


import com.bulatmain.authorization.application.service.AuthorizationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorityController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:test.properties")
public class AuthorityControllerTest    {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorizationService authorizer;

    final String path = "/api/auth";

    @Test
    public void AuthoriseOrganizerEditConference_Allow() throws Exception {
        given(authorizer.authorizeEditConference(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()))
                .willReturn(true);

        var response =    mockMvc.perform(
                get(path + "/edit",
                        "alice", "conf")
        );


        var mvcResult = response.andExpect(status().isOk()).andReturn();
        Assertions.assertEquals("true", mvcResult.getResponse().getContentAsString());
    }

}
