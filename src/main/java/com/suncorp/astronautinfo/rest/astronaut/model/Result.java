package com.suncorp.astronautinfo.rest.astronaut.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "url",
        "name",
        "status",
        "type",
        "date_of_birth",
        "date_of_death",
        "nationality",
        "bio",
        "twitter",
        "instagram",
        "wiki",
        "agency",
        "profile_image",
        "profile_image_thumbnail",
        "last_flight",
        "first_flight"
})
@Data
public class Result {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public String name;
    @JsonProperty("status")
    public Status status;
    @JsonProperty("type")
    public Type type;
    @JsonProperty("date_of_birth")
    public String dateOfBirth;
    @JsonProperty("date_of_death")
    public Object dateOfDeath;
    @JsonProperty("nationality")
    public String nationality;
    @JsonProperty("bio")
    public String bio;
    @JsonProperty("twitter")
    public Object twitter;
    @JsonProperty("instagram")
    public Object instagram;
    @JsonProperty("wiki")
    public String wiki;
    @JsonProperty("agency")
    public Agency agency;
    @JsonProperty("profile_image")
    public String profileImage;
    @JsonProperty("profile_image_thumbnail")
    public String profileImageThumbnail;
    @JsonProperty("last_flight")
    public String lastFlight;
    @JsonProperty("first_flight")
    public String firstFlight;
}
