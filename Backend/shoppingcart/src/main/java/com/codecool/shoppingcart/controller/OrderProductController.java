package com.codecool.shoppingcart.controller;

import com.codecool.shoppingcart.entity.OrderProduct;
import com.codecool.shoppingcart.repository.OrderRepository;
import com.codecool.shoppingcart.service.OrderProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path ="/cart")
public class OrderProductController {

    @Autowired
    OrderProductService orderProductService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping
    public List<OrderProduct> getAll() {
        return orderRepository.findAll();
    }

    @PostMapping
    public void addToCart(@RequestBody String payload) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(payload);
        String name = root.path("name").asText();
        double price = root.path("price").asDouble();
        String url = root.path("imgURL").asText();
        Integer quantity = 1;
        OrderProduct orderProduct = new OrderProduct(name, price, url, quantity);
        orderRepository.save(orderProduct);
    }

    @DeleteMapping
    public void deleteFromCar(@RequestBody String payload) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(payload);
        Long id = root.path("data").asLong();
        orderRepository.deleteById(id);
    }


    @PutMapping("/{id}")
    public void changeQuantity(@PathVariable("id") Long iD, @RequestBody String payload) {

        System.out.println(payload);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/cart").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");


            }
        };
    }
}

