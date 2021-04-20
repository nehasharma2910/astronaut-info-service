package com.suncorp.astronautinfo.rest.astronaut;

import com.suncorp.astronautinfo.rest.astronaut.AstronautController;
import com.suncorp.astronautinfo.rest.astronaut.AstronautService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AstronautControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AstronautService astronautService;
    @InjectMocks
    private AstronautController astronautController;
    private static final int ASTRONAUT_NUMBER = 123;
    private static final String CONTENT_TYPE_JSON ="application/json";

    @Before
    public void setup () {

        astronautController = new AstronautController(astronautService);
        mockMvc = MockMvcBuilders.standaloneSetup(astronautController).build();
    }

    @Test
    public void test_getAstronautList() throws Exception {

        mockMvc.perform(get("/api/v1/astronaut"))
                .andExpect(status().isOk());

        verify(astronautService, times(1)).getAstronautList();
    }

    @Test
    public void test_getAstronautDetails() throws Exception {

        mockMvc.perform(get("/api/v1/astronaut/" + ASTRONAUT_NUMBER ))
                .andExpect(status().isOk());

        verify(astronautService, times(1)).getAstronautDetails(ASTRONAUT_NUMBER);
    }



}
