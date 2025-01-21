INSERT INTO countries (id_country, name) VALUES (default, 'USA');
INSERT INTO countries (id_country, name) VALUES (default, 'France');
INSERT INTO countries (id_country, name) VALUES (default, 'Ucrania');
INSERT INTO countries (id_country, name) VALUES (default, 'Italy');
INSERT INTO countries (id_country, name) VALUES (default, 'Canada');


/* Roles */
INSERT INTO roles (id_role, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (id_role, name) VALUES (default, 'ROLE_ADMIN');

/* Users */
INSERT INTO users (id_user, username, password) VALUES (default, 'usuario1', '$2a$12$4MISlCi1OTqUs0CTC1DP0eaJWzXBEPWVECyv7R4XgAYzutBeJwG.S');
INSERT INTO users (id_user, username, password) VALUES (default, 'usuario2', '$2a$12$MfjlnZqAfH1zjlvW6AZ9eOP/V7orWpHDtuWmMUajFTVrBl805/jUO');

/* Profiles */
INSERT INTO profiles (id_profile,email, address, user_id, country_id) VALUES (default,'usuario1@mail.com', 'portal 1',1,2);
INSERT INTO profiles (id_profile,email, address, user_id, country_id) VALUES (default,'usuario2@mail.com', 'portal 1',2,3);

/* Roles Users */
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);