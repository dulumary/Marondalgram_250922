package com.marondal.marondalgram.user.service;

import com.marondal.marondalgram.common.SHA256HashingEncoder;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(
            String loginId
            , String password
            , String name
            , String email) {

        String encodedPassword = SHA256HashingEncoder.encode(password);

        int count = userRepository.insertUser(loginId, encodedPassword, name, email);

        if(count == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isDuplicateId(String loginId) {
        int count = userRepository.selectCountByLoginId(loginId);

        if(count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public User getUser(String loginId, String password) {

        String encodedPassword = SHA256HashingEncoder.encode(password);

        return userRepository.selectUser(loginId, encodedPassword);
    }


}
