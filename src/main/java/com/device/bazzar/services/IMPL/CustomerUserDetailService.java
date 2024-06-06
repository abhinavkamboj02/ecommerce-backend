package com.device.bazzar.services.IMPL;

import com.device.bazzar.exception.ResourceNotFoundException;
import com.device.bazzar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

//@Service
//public class CustomerUserDetailService implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByuserEmail(username).orElseThrow(()-> new ResourceNotFoundException("No User Found"));
//    }
//}
