package org.example.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api-endpoint}")
public class HomeController {

    @GetMapping("")
    public String index()
    {
        return "Hello from Server";
    }

    @GetMapping("/public")
    public String gotToPublic(){
        return "Public path";
    }

    @GetMapping("/private")
    public String goToPrivate(){
        return "Private path";
    }
}
