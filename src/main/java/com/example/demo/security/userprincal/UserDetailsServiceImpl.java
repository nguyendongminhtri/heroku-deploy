package com.example.demo.security.userprincal;


import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserServiceImpl userService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return UserPrinciple.build(user);
    }
    //Viết Hàm để lấy user hiện tại rồi thao tác với cơ sở dữ liệu
    public User getCurrentUser(){
        Optional<User> user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
           userName = ((UserDetails) principal).getUsername();
        }else {
            userName = principal.toString();
        }
        if(userRepository.existsByUsername(userName)){
            user = userService.findByUsername(userName);
        } else {
            user = Optional.of(new User());
            user.get().setUsername("Anonymous");
        }
        return user.get();
    }
}
