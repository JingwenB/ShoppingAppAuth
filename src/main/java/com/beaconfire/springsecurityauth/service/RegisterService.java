package com.beaconfire.springsecurityauth.service;

import com.beaconfire.springsecurityauth.dao.UserDao;
import com.beaconfire.springsecurityauth.domain.entity.Permission;
import com.beaconfire.springsecurityauth.domain.entity.User;
import com.beaconfire.springsecurityauth.exception.RegisterEmailDuplicateException;
import com.beaconfire.springsecurityauth.exception.RegisterUsernameDuplicateException;
import com.beaconfire.springsecurityauth.security.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void saveUser(User user) {
        List<String> usernames = userDao.getAllUsers()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());

        List<String> emails = userDao.getAllUsers()
                .stream()
                .map(User::getEmail)
                .collect(Collectors.toList());

        if(usernames.contains(user.getUsername())){
            throw new RegisterUsernameDuplicateException(
                    String.format("Username %s is duplicated, please choose another username", user.getUsername()));
        }

        if(emails.contains(user.getEmail())){
            throw new RegisterEmailDuplicateException(
                    String.format("Email %s is duplicated, please choose another email", user.getEmail()));
        }
        userDao.add(user);
    }



}
