package com.junitt.junitdemos.controller;

import com.junitt.junitdemos.UserDao;
import com.junitt.junitdemos.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FirstControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDao userDao;

    @Test
    void getTime() throws Exception {
        mockMvc.perform(get("/time")).andExpect(status().isOk());
    }

    @Test
    void addUser() {
    }

    @Test
    void getUser() throws Exception {
        User user = new User();
        user.setEmail("abcd@gmail.com");
        user.setUsername("abcd");
        user.setEnabled("true");
        Mockito.when(userDao.findById("abcd")).thenReturn(Optional.of(user));
        mockMvc.perform(get("/user/abcd")).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("abcd"))
                .andExpect(jsonPath("$.email").value("abcd@gmail.com"));
    }
}