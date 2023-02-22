package com.ecommerce.control;


import com.ecommerce.entity.UserInformation;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {


    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public RegisterController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

     @PostMapping("/api/register")
    public String saveUserDetails(@RequestBody UserInformation userInformation)
    {
        String password=userInformation.getPassword();
        userInformation.setPassword(passwordEncoder.encode(password));
        userRepository.save(userInformation);
        return "user saved";
    }
}
