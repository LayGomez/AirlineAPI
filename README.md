# AirlineAPI

# ENDPOINTS
## Airports

| function                  | ENDPOINT                                             |
|---------------------------|------------------------------------------------------|
| Show all airports         | GET localhost:8080/api/v1/airports                   |
| Search airport by name    | GET localhost:8080/api/v1/airports?name=roma         |
| Search airport by country | GET localhost:8080/api/v1/airports?countryName=italy |  
| Create airport            | POST localhost:8080/api/v1/airports                  |  
| Update airport            | PUT localhost:8080/api/v1/airports/{id}              |  
| Delete airport            | DELETE localhost:8080/api/v1/airports/{id}           |  

Create an airport
```
{
"name": "Miami International Airport",
"countryName": "USA"
}

```
## Countries



## Flights

| function                             | ENDPOINT                                                                          |
|--------------------------------------|-----------------------------------------------------------------------------------|
| Show all flights                     | GET localhost:8080/api/v1/flights                                                 |
| Search flight by origin country      | GET localhost:8080/api/v1/flights/search/by-origin-country?country= {country}     |
| Search flight by destination country | GET localhost:8080/api/v1/flights/search/by-destination-country?country={country} |  
| Create flight                        | POST localhost:8080/api/v1/airports                                               |  
| Update flight                        | PUT localhost:8080/api/v1/airports/{id}                                           |  
| Delete flight                        | DELETE localhost:8080/api/v1/airports/{id}                                        | 

Create a flight
```
{
    "originAirport": "Seattle International Airport",
    "destinationAirport": "Roma Fiumicino Airport",
    "departureDate": "2025-11-20T11:28",
    "arrivalDate": "2025-11-21T11:28",
    "capacity": 0
}
```