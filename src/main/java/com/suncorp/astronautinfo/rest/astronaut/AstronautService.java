package com.suncorp.astronautinfo.rest.astronaut;

import com.suncorp.astronautinfo.rest.astronaut.model.AstronautDto;
import com.suncorp.astronautinfo.rest.astronaut.model.AstronautResponse;
import com.suncorp.astronautinfo.rest.astronaut.model.Result;
import com.suncorp.astronautinfo.core.RestResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
@Slf4j
public class AstronautService {

    private String apiEndpoint;
    private final RestTemplate restTemplate;
    private List<AstronautDto> astronautDataList = new ArrayList<>();

    public AstronautService(RestTemplateBuilder builder,
                            @Value("${astronaut.service.endpoint}")
                                String apiEndpoint) {
        this.restTemplate = builder.errorHandler(new RestResponseErrorHandler()).build();
        this.apiEndpoint = apiEndpoint;
    }

    public List<AstronautDto> getAstronautList() {
        return getAstronautList(getApiUri(0));
    }

    public List<AstronautDto> getAstronautList(String url) {
        log.info("Inside getAstronautList with url:{}", url);
        ResponseEntity<AstronautResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AstronautResponse>() {
                });

        response.getBody().getResults().forEach(result -> astronautDataList.add(
                AstronautDto.builder()
                .name(result.getName())
                .nationality(result.getNationality())
                .profileImageThumbnail(result.getProfileImageThumbnail()).build()));

        if (response.getBody().getNext() != null) {
            getAstronautList(response.getBody().getNext());
        }

        return astronautDataList;
    }

    public AstronautDto getAstronautDetails(int astronautId) {
        String url = getApiUri(astronautId);
        log.info("Inside getAstronautDetails with url:{}", url);
        ResponseEntity<Result> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result>() {
                });

        return AstronautDto.builder()
                .name(result.getBody().getName())
                .profileImageThumbnail(result.getBody().getProfileImageThumbnail())
                .nationality(result.getBody().getNationality())
                .bio(result.getBody().getBio())
                .dateOfBirth(result.getBody().getDateOfBirth()).build();

    }

    private String getApiUri(int id) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", id==0?null:String.valueOf(id));

        UriComponentsBuilder builder  = UriComponentsBuilder.fromUriString(apiEndpoint)
                .queryParam("format","json")
                .queryParam("limit", "100");

        return builder.buildAndExpand(urlParams).toUriString();

    }
}
