package org.example.facade.encryptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class EncryptionFacadeTest {

    @Autowired
    private EncryptionFacade facade;

    @Test
    @DisplayName("Encode data with bcrypt")
    void testEncode() {
        String type = "bcrypt";
        String data = "password";

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String passwordEncoded = facade.encode(type, data);

        assertNotEquals(data, passwordEncoded);
        assertTrue(encoder.matches(data, passwordEncoded));
    }

    @Test
    @DisplayName("Decode data with Base 64")
    void testDecode() {
        String type = "base64";
        String data = "password";

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = encoder.encode(data.getBytes());
        String passwordEncoded = new String(bytes);

        String decodedPassword = facade.decode(type, passwordEncoded);

        assertEquals(data, decodedPassword);

    }

}