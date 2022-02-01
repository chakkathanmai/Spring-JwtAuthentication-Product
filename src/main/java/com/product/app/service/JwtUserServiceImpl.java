package com.product.app.service;

import com.product.app.model.JwtUser;
import com.product.app.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      JwtUser jwtUser= userRepository.findByUsername(username);
      if(jwtUser==null)
          throw  new UsernameNotFoundException("Username does not exists");
        UserDetails userDetails=new User(jwtUser.getUsername(),jwtUser.getPassword(),new ArrayList<>());
      return userDetails;
    }
    public JwtUser addUser(JwtUser user){
        return userRepository.save(user);
    }


}
