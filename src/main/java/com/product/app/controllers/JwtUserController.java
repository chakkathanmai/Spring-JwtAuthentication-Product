package com.product.app.controllers;

import com.product.app.model.JwtUser;
import com.product.app.service.JwtUserServiceImpl;
import com.product.app.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtUserController {
    @Autowired
    private JwtUserServiceImpl jwtUserServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil tokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody JwtUser request) {
        authenticate(request.getUsername(), request.getPassword());
        UserDetails details= jwtUserServiceImpl.loadUserByUsername(request.getUsername());
        String token= tokenUtil.generateToken(details);
        return ResponseEntity.ok(token);
    }
    private void authenticate(String username,String password){
       try{
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
       }catch(BadCredentialsException e){
           System.out.println("Invalid credentials");
       }catch (DisabledException e){
           System.out.println("disabled");
       }
    }

    @PostMapping("/register")
    public ResponseEntity<JwtUser> registerUser(@RequestBody JwtUser request) {
        String password = passwordEncoder.encode(request.getPassword());
        JwtUser user = new JwtUser(request.getUsername(), password);
        JwtUser nuser = jwtUserServiceImpl.addUser(user);
        return ResponseEntity.ok(nuser);
    }
}
