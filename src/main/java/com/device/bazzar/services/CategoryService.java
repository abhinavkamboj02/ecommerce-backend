package com.device.bazzar.services;

import com.device.bazzar.dtos.PageableResponse;
import com.device.bazzar.dtos.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, String categoryId);
    void deleteCategory(String categoryId);

    CategoryDto getCategoryById(String categoryId);
    PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);

}
