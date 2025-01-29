/* COUNTRIES */
INSERT INTO countries (id_country, name) VALUES (default, 'USA');
INSERT INTO countries (id_country, name) VALUES (default, 'France');
INSERT INTO countries (id_country, name) VALUES (default, 'Ucrania');
INSERT INTO countries (id_country, name) VALUES (default, 'Italy');
INSERT INTO countries (id_country, name) VALUES (default, 'Canada');


/* Airports */
INSERT INTO airport (id_airport, name, id_country) VALUES (default, 'Seattle International Airport', 1);
INSERT INTO airport (id_airport, name, id_country) VALUES (default, 'Roma Fiumicino Airport', 4);
INSERT INTO airport (id_airport, name, id_country) VALUES (default, 'Paris Orly Airport', 2);
INSERT INTO airport (id_airport, name, id_country) VALUES (default, 'Kiev Zhuliany International Airport', 3);

/* FLIGHTS */
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 1, 2, '2025-11-21 11:30:00', '2025-11-22 11:28:00', 15, 15, true);
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 2, 3, '2025-11-21 11:30:00', '2025-11-21 14:28:00', 15, 15, true);
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 3, 2,'2025-11-21 11:30:00', '2025-11-21 13:28:00', 15, 15, true);
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 4, 1, '2025-11-20 11:30:00', '2025-11-21 16:28:00', 15, 15, true);
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 1, 3, '2025-01-25 11:30:00', '2025-01-25 12:28:00', 15, 15, false);

/* Roles */
INSERT INTO roles (id_role, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (id_role, name) VALUES (default, 'ROLE_ADMIN');

/* Users */
INSERT INTO users (id_user, username, password) VALUES (default, 'usuario1', '$2a$12$4MISlCi1OTqUs0CTC1DP0eaJWzXBEPWVECyv7R4XgAYzutBeJwG.S');
INSERT INTO users (id_user, username, password) VALUES (default, 'admin1', '$2a$12$MfjlnZqAfH1zjlvW6AZ9eOP/V7orWpHDtuWmMUajFTVrBl805/jUO');

/* Profiles */
INSERT INTO profiles (id_profile,email, address, user_id, country_id) VALUES (default,'usuario1@mail.com', 'portal 1',1,2);
INSERT INTO profiles (id_profile,email, address, user_id, country_id) VALUES (default,'admin1@mail.com', 'portal 1',2,3);

/* Roles Users */
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);