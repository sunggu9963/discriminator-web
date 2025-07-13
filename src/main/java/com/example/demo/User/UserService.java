package com.example.demo.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserEntity save(UserDto userDto) {
        userDto.setPassword1(passwordEncoder.encode(userDto.getPassword1()));
        UserEntity userEntity = userMapper.toEntity(userDto);
        userRepository.save(userEntity);
        return userEntity;
    }
}
