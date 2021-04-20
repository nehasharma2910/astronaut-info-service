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
        "featured",
        "type",
        "country_code",
        "abbrev",
        "description",
        "administrator",
        "founding_year",
        "launchers",
        "spacecraft",
        "parent",
        "image_url"
})
@Data
public class Agency {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public String name;
    @JsonProperty("featured")
    public Boolean featured;
    @JsonProperty("type")
    public String type;
    @JsonProperty("country_code")
    public String countryCode;
    @JsonProperty("abbrev")
    public String abbrev;
    @JsonProperty("description")
    public String description;
    @JsonProperty("administrator")
    public String administrator;
    @JsonProperty("founding_year")
    public String foundingYear;
    @JsonProperty("launchers")
    public String launchers;
    @JsonProperty("spacecraft")
    public String spacecraft;
    @JsonProperty("parent")
    public Object parent;
    @JsonProperty("image_url")
    public Object imageUrl;
}
