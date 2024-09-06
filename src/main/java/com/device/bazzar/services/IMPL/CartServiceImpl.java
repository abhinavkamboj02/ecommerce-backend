package com.device.bazzar.services.IMPL;

import com.device.bazzar.dtos.AddItemToCartRequest;
import com.device.bazzar.dtos.CartDto;
import com.device.bazzar.entities.Cart;
import com.device.bazzar.entities.Product;
import com.device.bazzar.entities.User;
import com.device.bazzar.exception.BadApiRequest;
import com.device.bazzar.repositories.CartItemRepository;
import com.device.bazzar.repositories.UserRepository;
import com.device.bazzar.services.CartService;
import com.device.bazzar.entities.CartItems;
import com.device.bazzar.exception.ResourceNotFoundException;
import com.device.bazzar.repositories.CartRepository;
import com.device.bazzar.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
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
    @Autowired
    CartItemRepository cartItemRepository;
    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {

        int quantity = request.getQuantity();
        String productId = request.getProductId();


        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Invalid product Id"));
        User user =userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Invalid User ID"));

        AtomicReference<Boolean> productAvailable = new AtomicReference<>(false);

        Cart cart = null;
        try{
            cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("No cart Found"));;
        }catch (ResourceNotFoundException e){
            cart  = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCartedAt(new Date());
            cart.setUser(user);
        }

        List<CartItems> updatedItems =  cart.getItems().stream().map(item -> {
                   if (item.getProduct().getProductId().equals(productId)) {
                       item.setQuantity(quantity);
                       item.setTotalAmount(quantity*product.getPrice());
                       productAvailable.set(true);
                   }
          return item;
       }).collect(Collectors.toList());


       if(productAvailable.get() == false){
            CartItems cartItem = CartItems.builder()
                    //.cartItemId(UUID.randomUUID().toString())
                    .quantity(quantity)
                    .totalAmount(quantity*product.getPrice())
                    .product(product)
                    .cart(cart)
                    .build();
            cart.getItems().add(cartItem);
        }

        Cart updatedCart = cartRepository.save(cart);
        return modelMapper.map(updatedCart, CartDto.class);

    }
    @Override
    public void removeItemFromCart(String userId, int cartItemId ){
        //conditions
        CartItems cartItem1 = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("Cart Item not found !!"));
        cartItemRepository.delete(cartItem1);
    }
    @Override
    public void clearCart(String userId) {
        User user =userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No User Found with given Id"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("No cart found"));
        cart.getItems().clear();
        cartRepository.save(cart);

    }
    @Override
    public CartDto getUsercart(String UserId) {
        User user = userRepository.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("No user found with given Id"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("No cart Found"));
        return modelMapper.map(cart, CartDto.class);

    }
}
