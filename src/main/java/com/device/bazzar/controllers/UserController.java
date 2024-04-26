package com.device.bazzar.controllers;

import com.device.bazzar.dtos.ImageResponse;
import com.device.bazzar.dtos.PageableResponse;
import com.device.bazzar.dtos.UserDto;
import com.device.bazzar.services.FileService;
import com.device.bazzar.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    private String uploadImagePath;
    //create
    @PostMapping
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1 = userService.createUser(userDto);
        return  new ResponseEntity<>(userDto1, HttpStatus.CREATED);

    }
    //update
    @PutMapping("/{userId}")
    ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId){
        UserDto userDto1 = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{userId}")
    ResponseEntity<String> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Successfully Deleted!!!", HttpStatus.OK);
    }
    //Get all user
    @GetMapping()
    ResponseEntity<PageableResponse<UserDto>> getAllUser(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "userName", required = false) String sortBy,
            @RequestParam(defaultValue = "userName", required = false) String sortDir
            ){
        PageableResponse<UserDto> allUser= userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allUser, HttpStatus.OK);

    }
    //get single user by ID
    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUserByID(@PathVariable String userId){
        UserDto userDto1 = userService.getUserById(userId);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
    //get single user by email
    @GetMapping("/email/{userEmail}")
    ResponseEntity<UserDto> getUserByEmail(@PathVariable String userEmail){
        UserDto userDto1 = userService.getUserByEmail(userEmail);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
    //search users
    @GetMapping("/search/{keyword}")
    ResponseEntity<List<UserDto>> getUserByKeyword(@PathVariable String keyword){
        List<UserDto> allUser= userService.searchUsers(keyword);
        return new ResponseEntity<>(allUser, HttpStatus.OK);

    }
    //upload User Image
    @PostMapping("/image/{userId}")
    ResponseEntity<ImageResponse> uploadUserImage(@RequestParam MultipartFile userImageName, @PathVariable String userId) throws IOException {
       String imageName = fileService.uploadfile(userImageName, uploadImagePath );
       UserDto userDto1 = userService.getUserById(userId);
       userDto1.setUserImageName(imageName);
       UserDto userDto2 = userService.updateUser(userDto1, userId);
       ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
       return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }
    //serve User Image
    @GetMapping("/image/{userId}")
    void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto userdto1 = userService.getUserById(userId);
        InputStream resource = fileService.getResource(uploadImagePath, userdto1.getUserImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
