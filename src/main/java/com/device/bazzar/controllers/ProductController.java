package com.device.bazzar.controllers;

import com.device.bazzar.dtos.ImageResponse;
import com.device.bazzar.dtos.PageableResponse;
import com.device.bazzar.services.ProductService;
import com.device.bazzar.dtos.ProductDto;
import com.device.bazzar.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
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

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/product")
@Tag(name = "Product Controller", description = "REST APIs for Product operations")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @Value("${product.image.path}")
    private String path;

    //create
    @PostMapping
    @Operation(summary = "create Product")
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto productDto1 = productService.createProduct(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{product_Id}")
    @Operation(summary = "update Product")
    ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String product_Id){
        ProductDto productDto1 = productService.updateProduct(productDto, product_Id);
        return new ResponseEntity<>(productDto1, OK);
    }
    @GetMapping("/search/{subTittle}")
    @Operation(summary = "get product by subtitle")
    ResponseEntity<List<ProductDto>> getByTittle(@PathVariable String subTittle){
        List<ProductDto> list = productService.getByTittle(subTittle);
        return new ResponseEntity<>(list, OK);
    }
    //get all
    @GetMapping
    @Operation(summary = "get all Products")
    ResponseEntity<PageableResponse> getAll(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                            @RequestParam(defaultValue = "10", required = false) int pageSize,
                                            @RequestParam(defaultValue = "tittle", required = false) String sortBy,
                                            @RequestParam(defaultValue = "tittle", required = false) String sortDir){
        PageableResponse resp = productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(resp, OK);
    }
    //get islivetrue
    @GetMapping("/islive")
    @Operation(summary = "get all Live Products")
    ResponseEntity<List<ProductDto>> isLive(){
        List<ProductDto> list = productService.getByIsLiveTrue();
        return new ResponseEntity<>(list, OK);
    }
    //delete
    @DeleteMapping("/{product_Id}")
    @Operation(summary = "delete a Product")
    ResponseEntity<String> deleteProduct(@PathVariable String product_Id){
        productService.deleteProduct(product_Id);
        return new ResponseEntity<>("Deleted Successfully!!", OK);
    }

    //upload image
    @PostMapping("/image/{product_Id}")
    @Operation(summary = "Upload product image")
    ResponseEntity<ImageResponse> uploadImage(@RequestParam MultipartFile productImage, @PathVariable String product_Id) throws IOException {
        String imageName = fileService.uploadfile(productImage, path);
        ProductDto productDto = productService.findById(product_Id);
        productDto.setImageName(imageName);
        productService.updateProduct(productDto, product_Id);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).message("Successfully Added!").success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);


    }
    //serve image
    @GetMapping("/image/{product_Id}")
    @Operation(summary = "Serve product image")
    void serveUserImage(@PathVariable String product_Id, HttpServletResponse response) throws IOException {
        ProductDto productDto = productService.findById(product_Id);
        InputStream resource = fileService.getResource(path, productDto.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
    @PostMapping("/category/{categoryId}")
    @Operation(summary = "create product with category")
    ResponseEntity<ProductDto> createProductWithCategory(@RequestBody ProductDto productDto, @PathVariable  String categoryId){
        ProductDto productDto1 = productService.createProductWithCategory(productDto, categoryId);
        System.out.println(productDto1.toString());
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }
    @PutMapping("/{product_Id}/category/{categoryId}")
    @Operation(summary = "Update category in Product")
    ResponseEntity<ProductDto> updateCategoryInProduct(@PathVariable String product_Id, @PathVariable String categoryId){
        ProductDto productDto = productService.updateProductWithCategory(product_Id, categoryId);
        return new ResponseEntity<>(productDto, OK);
    }
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get producct by category")
    ResponseEntity<List<ProductDto>> productsWithGivenCategory(@PathVariable String categoryId){
        List<ProductDto> list = productService.categoryWiseProducts(categoryId);
        return new ResponseEntity<>(list, OK);
    }
    @GetMapping("/{productId}")
    @Operation(summary = "get product by productId")
    ResponseEntity<ProductDto> getProductById(@PathVariable String productId){
        ProductDto productDto = productService.getByProductId(productId);
        return new ResponseEntity<>(productDto, OK);
    }





}
