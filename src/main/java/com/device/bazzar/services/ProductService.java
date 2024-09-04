package com.device.bazzar.services;

import com.device.bazzar.dtos.PageableResponse;
import com.device.bazzar.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    // create product
    ProductDto createProduct(ProductDto productDto);

    //update product
    ProductDto updateProduct(ProductDto productDto, String product_Id);
    //get All product
    PageableResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir);

    // get by tittle
    List<ProductDto> getByTittle(String subTittle);

    // get by live
    List<ProductDto> getByIsLiveTrue();
    ProductDto findById(String product_Id);

    //delete product
    void deleteProduct(String product_Id);
    //add product with category
    ProductDto createProductWithCategory(ProductDto productDto, String categoryId);
    ProductDto updateProductWithCategory(String productId, String categoryId);
    List<ProductDto> categoryWiseProducts(String categoryId);

    ProductDto getByProductId(String productId);
}
