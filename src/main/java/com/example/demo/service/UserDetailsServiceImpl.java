package com.example.demo.service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        logger.info("Entering in loadUserByUsername Method...");
        UserInfo user = userRepository.findByUsername(username);
        if(user == null){
//            logger.info("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
//        logger.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(user);
    }

}
