package com.bsg5.chapter6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = GatewayAppWebConfig.class)
public class TestGreetingController extends AbstractTestNGSpringContextTests {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Test
  public void greetingTest() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    this.mockMvc.perform(get("/greeting")
        .accept(MediaType.ALL))
        .andExpect(status().isOk())

;
  }
}
