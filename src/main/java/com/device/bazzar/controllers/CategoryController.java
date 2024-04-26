package com.device.bazzar.controllers;

import com.device.bazzar.dtos.CategoryDto;
import com.device.bazzar.dtos.ImageResponse;
import com.device.bazzar.dtos.PageableResponse;
import com.device.bazzar.services.CategoryService;
import com.device.bazzar.services.FileService;
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

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileService fileService;
    @Value("${category.cover.image.path}")
    private String imagePath;
    //create
    @PostMapping
    ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{categoryId}")
    ResponseEntity<String> deletecategory(@PathVariable String categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("category deleted Successfully!!", HttpStatus.OK);
    }
    //getbyId
    @GetMapping("/{categoryId}")
    ResponseEntity<CategoryDto> getbyId(@PathVariable String categoryId){
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
    //getALL
    @GetMapping
    ResponseEntity<PageableResponse> getAll(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                            @RequestParam(defaultValue = "10", required = false) int pageSize,
                                            @RequestParam(defaultValue = "categoryTittle", required = false) String sortBy,
                                            @RequestParam(defaultValue = "categoryTittle", required = false) String sortDir){
        PageableResponse resp = categoryService.getAllCategory(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }
    //upload image;
    @PostMapping("/image/{categoryId}")
    ResponseEntity<ImageResponse> uploadimage(@RequestParam MultipartFile image, @PathVariable String categoryId) throws IOException {
        String imageName = fileService.uploadfile(image,imagePath);
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        categoryDto.setCoverImage(imageName);
        CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryDto, categoryId);
        ImageResponse response = ImageResponse.builder().imageName(imageName).message("cover image updated!!").success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    @GetMapping("/image/{categoryId}")
    void serveUserImage(@PathVariable String categoryId, HttpServletResponse response) throws IOException {
        CategoryDto categorydto1 = categoryService.getCategoryById(categoryId);
        InputStream resource = fileService.getResource(imagePath, categorydto1.getCoverImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
