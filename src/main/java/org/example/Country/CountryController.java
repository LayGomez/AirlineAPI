package org.factoriaf5.countries;

import java.util.List;

import org.example.Country.Country;
import org.example.Country.CountryService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api-endpoint}/countries")
public class CountryController {

    private CountryService services;

    public CountryController(CountryService services) {
        this.services = services;
    }

    @GetMapping
    public List<Country> index() {
        return services.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Country> show(@PathVariable("id") Long id) {
        Country country = services.getById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(country);
    }

    @PostMapping
    public ResponseEntity<Country> create(@RequestBody Country newCountry) {
        Country country = services.store(newCountry);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(country);
    }

}
