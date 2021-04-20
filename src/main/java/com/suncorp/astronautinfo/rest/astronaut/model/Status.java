package com.suncorp.astronautinfo.rest.astronaut.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name"
})
@Data
public class Status {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
}
