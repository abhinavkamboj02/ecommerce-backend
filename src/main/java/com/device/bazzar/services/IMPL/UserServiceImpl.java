package com.device.bazzar.services.IMPL;

import com.device.bazzar.dtos.PageableResponse;
import com.device.bazzar.dtos.UserDto;
import com.device.bazzar.entities.User;
import com.device.bazzar.exception.ResourceNotFoundException;
import com.device.bazzar.helper.Helper;
import com.device.bazzar.repositories.UserRepository;
import com.device.bazzar.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
    @Value("${user.profile.image.path}")
    private String imagePath;

    @Override
    public UserDto createUser(UserDto userdto) {
        User user = modelMapper.map(userdto, User.class);
        String id = UUID.randomUUID().toString();
        user.setUserId(id);
       // user.setUserPassword(passwordEncoder.encode(userdto.getUserPassword()));
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("No user found"));
        user.setUserName(userDto.getUserName());
        user.setUserAbout(userDto.getUserAbout());
        user.setUserGender(userDto.getUserGender());
       // user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        user.setUserImageName(userDto.getUserImageName());
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found with given User ID"));
        String imageName = user.getUserImageName();
        userRepository.delete(user);
        String fullPath = imagePath + File.separator + imageName;
        try {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);
        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found with given User ID"));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String userEmail) {
        User user = userRepository.findByuserEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("No user found with given email"));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> searchUsers(String keyword) {
        List<User> users = userRepository.findByuserNameContaining(keyword);
        List<UserDto> usersDto = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return usersDto;
    }
}
