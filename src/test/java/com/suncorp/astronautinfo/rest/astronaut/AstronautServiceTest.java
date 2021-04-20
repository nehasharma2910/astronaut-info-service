package com.suncorp.astronautinfo.rest.astronaut;

import com.suncorp.astronautinfo.rest.astronaut.model.AstronautResponse;
import com.suncorp.astronautinfo.rest.astronaut.model.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AstronautServiceTest {

    @Mock
    AstronautService astronautService;
    @Mock
    RestTemplate restTemplate;
    @Mock
    RestTemplateBuilder restTemplateBuilder;
    @Captor
    ArgumentCaptor<String> buildUrlCaptor;
    @Captor
    ArgumentCaptor<HttpMethod> httpMethodArgumentCaptor;


    @Before
    public void setup() {
        String baseUrl = "http://spacelaunchnow.me/api/3.5.0/astronaut/{id}";
        when(restTemplateBuilder.errorHandler(any())).thenReturn(restTemplateBuilder);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);

        astronautService = new AstronautService(restTemplateBuilder, baseUrl);
    }

    @Test
    public void test_getAstronautDetails() {
        Result result = new Result();
        ResponseEntity<Result> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        when(restTemplate.exchange(
                buildUrlCaptor.capture(),
                httpMethodArgumentCaptor.capture(),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(responseEntity);

        astronautService.getAstronautDetails(123);

        String buildUrl = "http://spacelaunchnow.me/api/3.5.0/astronaut/123?format=json&limit=100";
        assertThat(buildUrlCaptor.getValue(), is(buildUrl));
        assertThat(httpMethodArgumentCaptor.getValue(), is(HttpMethod.GET));
    }

    @Test
    public void test_getAstronautList() {
        AstronautResponse astronautResponse = new AstronautResponse();
        Result result = new Result();
        astronautResponse.setResults(Arrays.asList(result));
        ResponseEntity<AstronautResponse> responseEntity = new ResponseEntity<>(astronautResponse, HttpStatus.OK);
        when(restTemplate.exchange(
                buildUrlCaptor.capture(),
                httpMethodArgumentCaptor.capture(),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(responseEntity);

        astronautService.getAstronautList();

        String buildUrl = "http://spacelaunchnow.me/api/3.5.0/astronaut/?format=json&limit=100";
        assertThat(buildUrlCaptor.getValue(), is(buildUrl));
        assertThat(httpMethodArgumentCaptor.getValue(), is(HttpMethod.GET));
    }

}
