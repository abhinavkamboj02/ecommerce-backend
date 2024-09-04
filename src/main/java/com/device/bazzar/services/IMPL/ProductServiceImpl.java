package com.device.bazzar.services.IMPL;

import com.device.bazzar.dtos.PageableResponse;
import com.device.bazzar.services.CategoryService;
import com.device.bazzar.services.ProductService;
import com.device.bazzar.dtos.ProductDto;
import com.device.bazzar.entities.Category;
import com.device.bazzar.entities.Product;
import com.device.bazzar.exception.ResourceNotFoundException;
import com.device.bazzar.helper.Helper;
import com.device.bazzar.repositories.CategoryRepository;
import com.device.bazzar.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;
    @Value("${product.image.path}")
    private String path;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product.setProductId(UUID.randomUUID().toString());
        Product productReturn = productRepository.save(product);
        ProductDto returnProductDto = modelMapper.map(productReturn, ProductDto.class);
        return returnProductDto;
    }
    @Override
    public ProductDto updateProduct(ProductDto productDto, String product_Id) {
        Product product = productRepository.findById(product_Id).orElseThrow(()-> new ResourceNotFoundException("No product Found"));
        product.setDescription(productDto.getDescription());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setTittle(productDto.getTittle());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setAddedDate(productDto.getAddedDate());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setImageName(productDto.getImageName());
        Product productReturn = productRepository.save(product);
        ProductDto returnProductDto = modelMapper.map(productReturn, ProductDto.class);
        return returnProductDto;

    }

    @Override
    public PageableResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public List<ProductDto> getByTittle(String subTittle) {
        List<Product> list = productRepository.findByTittleContaining(subTittle);
        List<ProductDto> listDto = list.stream().map(x -> modelMapper.map(x, ProductDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public List<ProductDto> getByIsLiveTrue() {
        List<Product> list = productRepository.findByLiveTrue();
        List<ProductDto> listDto = list.stream().map(x -> modelMapper.map(x, ProductDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public ProductDto findById(String product_Id) {
        Product product = productRepository.findById(product_Id).orElseThrow(()-> new ResourceNotFoundException("No User Found"));
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    @Override
    public void deleteProduct(String product_Id) {
        Product product = productRepository.findById(product_Id).orElseThrow(()-> new ResourceNotFoundException("No product Found"));
        String imageName = product.getImageName();
        productRepository.delete(product);
        String fullPath = path + File.separator + imageName;
        try {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDto createProductWithCategory(ProductDto productDto, String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category is not found with given category ID"));
        Product product = modelMapper.map(productDto, Product.class);
        product.setProductId(UUID.randomUUID().toString());
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        ProductDto returnProductDto = modelMapper.map(savedProduct, ProductDto.class);
        return returnProductDto;
    }

    @Override
    public ProductDto updateProductWithCategory(String productId, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category is not found with given category ID"));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("No product found"));
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> categoryWiseProducts(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Invalid category Id"));
        List<Product> productList = productRepository.findByCategory(category);
        List<ProductDto> returnProductDtoList = productList.stream().map(x -> modelMapper.map(x, ProductDto.class)).collect(Collectors.toList());
        return returnProductDtoList;
    }

    @Override
    public ProductDto getByProductId(String productId) {

        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Invalid product Id"));
        return modelMapper.map(product, ProductDto.class);

    }

}
