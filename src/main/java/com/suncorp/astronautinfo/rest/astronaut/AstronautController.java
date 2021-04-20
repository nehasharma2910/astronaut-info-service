package com.suncorp.astronautinfo.rest.astronaut;

import com.suncorp.astronautinfo.rest.astronaut.model.AstronautDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value="api/v1",  produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AstronautController {

    private final AstronautService astronautService;

    @GetMapping(value = "/astronaut")
    public ResponseEntity<List<AstronautDto>> getAstronautList() {
        return new ResponseEntity<>(astronautService.getAstronautList(), HttpStatus.OK);
    }

    @GetMapping(value = "/astronaut/{id}")
    public ResponseEntity<AstronautDto> getAstronautDetail(@PathVariable("id") @Min(1) int astronautId) {
        return new ResponseEntity<>(astronautService.getAstronautDetails(astronautId), HttpStatus.OK);
    }
}
