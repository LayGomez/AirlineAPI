package org.example.Profiles;

import org.example.Users.User;
import org.springframework.web.multipart.MultipartFile;

public record ProfileRequest(
        User user,
        String email,
        String country,
        String address,
        MultipartFile file
) {
}
