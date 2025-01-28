package org.example.register;

import org.example.Roles.RoleService;
import org.example.Users.User;
import org.example.Users.UserDto;
import org.example.Users.UserExceptions.UsernameAlreadyExistsException;
import org.example.Users.UserRepository;
import org.example.facade.encryptions.IEncryptFacade;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final IEncryptFacade encryptFacade;

    public RegisterService(UserRepository userRepository, RoleService roleService, IEncryptFacade encryptFacade) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encryptFacade = encryptFacade;
    }

    public Map<String, String> save(UserDto userData) {
        if (userRepository.existsByUsername(userData.username())) {
            throw new UsernameAlreadyExistsException("The username already exists.");
        }

        String passwordDecoded = encryptFacade.decode("base64",userData.password());

        System.out.println("<------------ " + passwordDecoded);

        String passwordEncoded = encryptFacade.encode("bcrypt", passwordDecoded);

        User newUser = new User(userData.username(), passwordEncoded);
        newUser.setRoles(roleService.assignDefaultRole());

        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");

        return response;
    }
}
