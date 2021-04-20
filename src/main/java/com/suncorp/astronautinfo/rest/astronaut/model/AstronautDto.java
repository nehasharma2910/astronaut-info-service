package com.suncorp.astronautinfo.rest.astronaut.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include. NON_NULL)
public class AstronautDto {

    private String name;
    private String nationality;
    private String profileImageThumbnail;
    private String bio;
    private String dateOfBirth;
}
