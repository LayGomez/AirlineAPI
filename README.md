# AirlineAPI ✈️
This project is a Flight Booking System developed using Java and the Spring Framework. The system allows users to manage their flight reservations in a secure and efficient manner. It incorporates features such as:

- Authentication via Basic Authentication to ensure that only authorized users can access specific endpoints. 
- Role-Based Access Control (RBAC), where users can view their own reservations, and administrators can view all bookings in the system. 
- CRUD operations (Create, Read, Update, Delete) for managing flight bookings. 
- Reservation Locking: To avoid overbooking, a feature has been implemented to temporarily lock seats for 15 minutes once a user starts making a booking, guaranteeing the availability of seats. 
- Dynamic Seat Management: When a reservation is made or canceled, the number of available seats for a particular flight is updated accordingly.

The system is designed to support multiple users (with the role of "USER") and administrative users (with the role of "ADMIN"). Users can create, view, update, or cancel their own flight bookings, while administrators have access to all bookings in the system.
## E-R model 

![ER_Ailine.png](/ER_Airline.png)

# 🎯 ENDPOINTS

## Airports

| function                  | ENDPOINT                                             |
|---------------------------|------------------------------------------------------|
| Show all airports         | GET localhost:8080/api/v1/airports                   |
| Search airport by name    | GET localhost:8080/api/v1/airports?name=roma         |
| Search airport by country | GET localhost:8080/api/v1/airports?countryName=italy |  
| Create airport            | POST localhost:8080/api/v1/airports                  |  
| Update airport            | PUT localhost:8080/api/v1/airports/{id}              |  
| Delete airport            | DELETE localhost:8080/api/v1/airports/{id}           |  

**Create an airport**
```JSON
{
"name": "Miami International Airport",
"countryName": "USA"
}

```
##  🇪🇸 Countries

| function             | ENDPOINT                                 |
|----------------------|------------------------------------------|
| Show all countries   | GET localhost:8080/api/v1/countries      |
| Search country by id | GET localhost:8080/api/v1/countries/{id} |  
| Create country       | POST localhost:8080/api/v1/countries     |  

## ✈️ Flights

| function                             | ENDPOINT                                                                          |
|--------------------------------------|-----------------------------------------------------------------------------------|
| Show all flights                     | GET localhost:8080/api/v1/flights                                                 |
| Search flight by origin country      | GET localhost:8080/api/v1/flights/search/by-origin-country?country= {country}     |
| Search flight by destination country | GET localhost:8080/api/v1/flights/search/by-destination-country?country={country} |  
| Create flight                        | POST localhost:8080/api/v1/airports                                               |  
| Update flight                        | PUT localhost:8080/api/v1/airports/{id}                                           |  
| Delete flight                        | DELETE localhost:8080/api/v1/airports/{id}                                        | 

Create a flight
```JSON
{
    "originAirport": "Seattle International Airport",
    "destinationAirport": "Roma Fiumicino Airport",
    "departureDate": "2025-11-20T11:28",
    "arrivalDate": "2025-11-21T11:28",
    "capacity": 15
}
```
## Booking


| function         | ENDPOINT                                           |
|------------------|----------------------------------------------------|
| Show my bookings | GET localhost:8080/api/v1/bookings/my-reservations |
| Create a booking | POST localhost:8080/api/v1/bookings                |  
| Update a booking | PUT localhost:8080/api/v1/bookings/{id}            |  
| Delete a booking | DELETE localhost:8080/api/v1/bookings/{id}         | 

Create a booking
```JSON
{
"username": "usuario1",
"seats": 4,
"id_flight": 1
}
```
JSON Response
```JSON
{
"id": 1,
"username": "usuario1",
"seats": 4,
"flight": {
"id": 1,
"originAirport": "Seattle International Airport",
"destinationAirport": "Roma Fiumicino Airport",
"departureDate": "2025-11-21T11:30:00",
"arrivalDate": "2025-11-22T11:28:00",
"availableSeats": 11,
"available": true
},
"date": "2025-01-30T00:09:35.81272084"
}
```
## ✈️ Automated Tasks

This project includes two automated tasks to manage flight availability and reservations efficiently.<br>
### ⏳ Flight Availability Check (Every 30 seconds)

- Runs every 30,000 milliseconds (30 seconds).
- Checks if flights meet the conditions to be available:<br>
   ✅ Future date (the flight has not departed). <br>
   ✅ Seats available (there are still open seats).

### 🎟️ Temporary Seat Reservation (15 minutes)

- When a flight is being booked, the selected seats are reserved for 15 minutes.
- If the reservation is not completed within 15 minutes, the seats are released.

These automated tasks ensure a smooth booking process and real-time flight availability updates! 🚀