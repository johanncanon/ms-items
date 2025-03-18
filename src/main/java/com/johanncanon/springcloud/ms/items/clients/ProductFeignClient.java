package com.johanncanon.springcloud.ms.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.johanncanon.springcloud.ms.items.models.Product;

@FeignClient( url = "http://localhost:8081/api/v1/products" , name = "ms-products" )
public interface ProductFeignClient {

    @GetMapping( "/all-products" )
    List<Product> findAll();

    @GetMapping( "/{id}" )
    Product details( @PathVariable("id") Long id );

}
