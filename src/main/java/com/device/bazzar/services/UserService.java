package com.device.bazzar.services;

import com.device.bazzar.dtos.PageableResponse;
import com.device.bazzar.dtos.UserDto;

import java.util.List;

public interface UserService {
    // CREATE
    UserDto createUser(UserDto userdto);
    //UPDATE
    UserDto updateUser(UserDto userdto, String userId);

    //DELETE
    void deleteUser(String userId);
    //GET ALL USER
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //GET SINGLE USER BY ID
    UserDto getUserById(String userId);
    //GET SINGLE USER BY EMAIL
    UserDto getUserByEmail(String userEmail);
    //SEARCH USERS
    List<UserDto> searchUsers(String keyword);
}
