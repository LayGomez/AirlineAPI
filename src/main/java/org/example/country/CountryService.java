package org.example.country;

import org.example.country.CountryExceptions.CountryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> getAll() {
        List<Country> countries = repository.findAll();
        return countries;
    }

    public Country getById(Long id) {
        Country country = repository.findById(id).orElseThrow(() -> new CountryNotFoundException("Country not found"));
        return country;
    }

    public Country store(Country newCountry) {
        Country countryStored = repository.save(newCountry);
        return countryStored;
    }
}
