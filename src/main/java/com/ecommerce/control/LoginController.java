package com.ecommerce.control;

import com.ecommerce.entity.Role;
import com.ecommerce.entity.UserInformation;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public LoginController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }


    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage()
    {
        return "register";
    }
    @PostMapping("/register")
    public String registerTheUser(@ModelAttribute("user") UserInformation user, HttpServletRequest request) throws ServletException {
        String password=user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        Role role=new Role();
        role.setRole("USER");
        List<Role> roleList=new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);
        userRepository.save(user);
        request.login(user.getUserName(),password);
        return "redirect:/";
    }
}
