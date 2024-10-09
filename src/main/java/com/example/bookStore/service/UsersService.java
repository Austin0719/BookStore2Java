package com.example.bookStore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookStore.entity.Users;
import com.example.bookStore.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsersService {
	
	 @Autowired
	 private PasswordEncoder pwdEncoder;
	 
	 @Autowired
	 private UsersRepository uRepo;
	 
	 
    public Users createUsers(String phoneNumber, String password,String userName) {

        // 將密碼加密
        String encodedPwd = pwdEncoder.encode(password);
        Users users = new Users();
        users.setPhoneNumber(phoneNumber);
        users.setPassword(encodedPwd);
        users.setUserName(userName);
        return uRepo.save(users);
    }
    
    public Users login(String phoneNumber, String loginPwd) {
        Optional<Users> optional = uRepo.findByPhoneNumber(phoneNumber);

        if (optional.isPresent()) {
            Users dbUser = optional.get();
            String dbPwd = dbUser.getPassword();
            boolean result = pwdEncoder.matches(loginPwd, dbPwd);

            if (result) {
                return dbUser;
            }
        }
        return null;
    }
}
