package com.ecommerce.security;

import com.ecommerce.entity.Role;
import com.ecommerce.entity.UserInformation;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
   private UserRepository userRepository;

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token=(OAuth2AuthenticationToken) authentication;
        String email=token.getPrincipal().getAttributes().get("email").toString();
        Map<String,Object> map=token.getPrincipal().getAttributes();
        System.out.println(map);
        if(userRepository.findByUserName(email).isPresent())
        {

        }
        else {
            UserInformation user=new UserInformation();
            user.setFirstName("gunal");
            user.setLastName(token.getPrincipal().getName());
            System.out.println(token.getPrincipal().getName());
            user.setUserName(email);
            Role role=new Role();
            role.setRole("USER");
            List<Role> roleList=new ArrayList<>();
            roleList.add(role);
            user.setRoles(roleList);
            userRepository.save(user);
        }
        redirectStrategy.sendRedirect(request,response,"/");
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }


}
