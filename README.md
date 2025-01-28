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
