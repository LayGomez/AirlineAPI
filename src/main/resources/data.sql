/* COUNTRIES */
INSERT INTO countries (id_country, name)
VALUES (1, 'USA')
ON CONFLICT DO NOTHING;

INSERT INTO countries (id_country, name)
VALUES (2, 'France')
ON CONFLICT DO NOTHING;

INSERT INTO countries (id_country, name)
VALUES (3, 'Ucrania')
ON CONFLICT DO NOTHING;

INSERT INTO countries (id_country, name)
VALUES (4, 'Italy')
ON CONFLICT DO NOTHING;

INSERT INTO countries (id_country, name)
VALUES (5, 'Canada')
ON CONFLICT DO NOTHING;



/* Airports*/
INSERT INTO airport (id_airport, name, id_country)
VALUES (1, 'Seattle International Airport', 1)
ON CONFLICT DO NOTHING;

INSERT INTO airport (id_airport, name, id_country)
VALUES (2, 'Roma Fiumicino Airport', 4)
ON CONFLICT DO NOTHING;

INSERT INTO airport (id_airport, name, id_country)
VALUES (3, 'Paris Orly Airport', 2)
ON CONFLICT DO NOTHING;

INSERT INTO airport (id_airport, name, id_country)
VALUES (4, 'Kiev Zhuliany International Airport', 3)
ON CONFLICT DO NOTHING;

/* FLIGHTS */
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 1, 2, '2025-11-21 11:30:00', '2025-11-22 11:28:00', 15, 15, true)
ON CONFLICT DO NOTHING;
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 2, 3, '2025-11-21 11:30:00', '2025-11-21 14:28:00', 15, 15, true)
ON CONFLICT DO NOTHING;

INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 3, 2,'2025-11-21 11:30:00', '2025-11-21 13:28:00', 15, 15, true)
ON CONFLICT DO NOTHING;
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 4, 1, '2025-11-20 11:30:00', '2025-11-21 16:28:00', 15, 15, true)
ON CONFLICT DO NOTHING;
INSERT INTO flights (id_flight, origin_id, destination_id, departure_date, arrival_date, capacity, available_seats, is_available)
VALUES (default, 1, 3, '2025-01-25 11:30:00', '2025-01-25 12:28:00', 15, 15, true)
ON CONFLICT DO NOTHING;


/* Roles */
INSERT INTO roles (id_role, name)
VALUES (1, 'ROLE_USER')
ON CONFLICT DO NOTHING;
INSERT INTO roles (id_role, name)
VALUES (2, 'ROLE_ADMIN')
ON CONFLICT DO NOTHING;

/* Users */
INSERT INTO users (id_user, username, password)
VALUES (1, 'usuario1', '$2a$12$4MISlCi1OTqUs0CTC1DP0eaJWzXBEPWVECyv7R4XgAYzutBeJwG.S')
ON CONFLICT DO NOTHING;

INSERT INTO users (id_user, username, password)
VALUES (2, 'admin1', '$2a$12$MfjlnZqAfH1zjlvW6AZ9eOP/V7orWpHDtuWmMUajFTVrBl805/jUO')
ON CONFLICT DO NOTHING;

/* Profiles */
INSERT INTO profiles (id_profile,email, address, user_id, country_id)
VALUES (default,'usuario1@mail.com', 'portal 1',(SELECT id_user FROM users WHERE id_user = 1),2)
ON CONFLICT DO NOTHING;


INSERT INTO profiles (id_profile,email, address, user_id, country_id)
VALUES (default,'admin1@mail.com', 'portal 1',(SELECT id_user FROM users WHERE id_user = 2),3)
ON CONFLICT DO NOTHING;


/* Roles Users */
INSERT INTO roles_users (role_id, user_id)
VALUES (1, 1)
ON CONFLICT DO NOTHING;

INSERT INTO roles_users (role_id, user_id)
VALUES (2, 2)
ON CONFLICT DO NOTHING;
