package com.example.L13.library.service;

import com.example.L13.library.dto.UserDTO;
import com.example.L13.library.entity.User;
import com.example.L13.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * <p>
     *     This method is used to create / persist new user object
     * </p>
     * @param userRequestDto
     * @return - userInfo without Id
     */
    public User createUser(UserDTO userRequestDto) {
        User userInfo = userRequestDto.toUser();
        userRepository.save(userInfo);
        return userInfo;
    }


    /**
     * 10000 of records
     *  --> (0,20) (21,40)
     *
     *     limit 20
     *     limit 21 40
     *
     * @return
     */
    public List<User> fetchAllUsers(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20);
        return userRepository.findAll(pageable).getContent();
    }

    public Optional<User> fetchOneById(Integer id) {
        return userRepository.findById(id);
    }
}
