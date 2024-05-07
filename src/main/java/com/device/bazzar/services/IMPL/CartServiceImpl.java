package com.device.bazzar.services.IMPL;

import com.device.bazzar.dtos.AddItemToCartRequest;
import com.device.bazzar.dtos.CartDto;
import com.device.bazzar.entities.Cart;
import com.device.bazzar.entities.Product;
import com.device.bazzar.entities.User;
import com.device.bazzar.exception.BadApiRequest;
import com.device.bazzar.repositories.UserRepository;
import com.device.bazzar.services.CartService;
import com.device.bazzar.entities.CartItems;
import com.device.bazzar.exception.ResourceNotFoundException;
import com.device.bazzar.repositories.CartRepository;
import com.device.bazzar.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
        int quantity = request.getQuantity();
        String productId = request.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException());
        User user =userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException());
        Cart cart = null;
        AtomicReference<Boolean> productAvailable = new AtomicReference<>(true);
        try{
            cart = cartRepository.findByUser(user);
        }catch (ResourceNotFoundException e){
            cart  = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCartedAt(new Date());
            cart.setUser(user);
        }
      List<CartItems> updatedItems =  cart.getItems().stream().map(item -> {
                   if (item.getProduct().getProduct_Id().equals(product.getProduct_Id())) {
                       item.setQuantity(quantity);
                       item.setTotalAmount(quantity*product.getPrice());
                       productAvailable.set(true);
                   }
          return item;
       }).collect(Collectors.toList());

       if(!productAvailable.get()){
           CartItems cartItem = CartItems.builder()
                   .quantity(quantity)
                   .totalAmount(quantity*product.getPrice())
                   .product(product)
                   .cart(cart)
                   .build();
           cart.getItems().add(cartItem);
       }else{
           cart.setItems(updatedItems);
       }
       Cart savedCart = cartRepository.save(cart);
       return modelMapper.map(savedCart, CartDto.class);






    }

    @Override
    public CartDto removeItemFromCart(String userId, AddItemToCartRequest request) {
//        if(request.getQuantity() <0)  throw new BadApiRequest("Invalid quantity");
        int quantity = request.getQuantity();
        String productId = request.getProductId();

        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("No Product Found"));
        User user =userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No User Found"));
        Cart cart = cartRepository.findByUser(user);
//        if(quantity < 0){
//            new BadApiRequest();
//        }
        if(quantity <0) { throw new BadApiRequest();
        } else if (quantity == 0)
        {
                List<CartItems> updatedCartItems = cart.getItems().stream()
                        .filter(item -> item.getProduct().getProduct_Id()!=(product.getProduct_Id()))
                        .collect(Collectors.toList());
                cart.setItems(updatedCartItems);
        }
        else{
            List<CartItems> updatedCartItems = cart.getItems().stream().map(item-> {
                if(item.getProduct().getProduct_Id().equals(product.getProduct_Id())){
                    item.setQuantity(quantity);
                    item.setTotalAmount(quantity * product.getPrice());
                }
                return item;
            }).collect(Collectors.toList());
            cart.setItems(updatedCartItems);
        }
        Cart savedCart = cartRepository.save(cart);
        return modelMapper.map(savedCart, CartDto.class);

    }

    @Override
    public void clearCart(String userId) {
        User user =userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException());
        Cart cart = cartRepository.findByUser(user);
        cart.getItems().clear();
        cartRepository.save(cart);

    }
}
