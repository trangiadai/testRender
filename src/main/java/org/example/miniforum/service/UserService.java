package org.example.miniforum.service;

import lombok.extern.slf4j.Slf4j;
import org.example.miniforum.dto.request.UserLogin;
import org.example.miniforum.dto.request.UserRequest;
import org.example.miniforum.dto.response.LoginResponse;
import org.example.miniforum.dto.response.UserResponse;
import org.example.miniforum.exception.AppException;
import org.example.miniforum.exception.ErrorCode;
import org.example.miniforum.mapper.UserMapper;
import org.example.miniforum.model.User;
import org.example.miniforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private UserMapper userMapper;

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public LoginResponse login(UserLogin userLogin) {

        if(userRepository.existsByUsername(userLogin.getUsername())){

            User user = userRepository.findByUsername(userLogin.getUsername())
                    .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXIST));

            if(userLogin.getPassword().equals(user.getPassword())){
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setId(user.getId());
                loginResponse.setAccountName(user.getUsername());
                return loginResponse;
            }else{
                throw new AppException(ErrorCode.PASSWORD_INCORRECT);
            }
        }else throw new AppException(ErrorCode.USER_NOT_EXIST);

    }

    public UserResponse setAvt(MultipartFile avtImg, int userId,
                                    String accountName, String email,
                                        String phone) throws IOException {

        User user = userRepository.findById(userId).orElseThrow();
        String imgUrl = cloudinaryService.uploadImage(avtImg);
        user.setAvartarImgUrl(imgUrl);
        if(accountName != null && !accountName.isEmpty()) user.setAccountName(accountName);
        log.info("user account name: {}", user.getAccountName());
        if(email != null && !email.isEmpty()) user.setEmail(email);
        log.info("user email: {}", user.getEmail());
        return userMapper.toUserResponse(userRepository.save(user));
    }



}
