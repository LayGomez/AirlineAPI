package org.example.register;

import org.example.Users.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("${api-endpoint}/register")
public class RegisterController {
    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDto newUser){
        Map<String, String> reponse = service.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }
}
