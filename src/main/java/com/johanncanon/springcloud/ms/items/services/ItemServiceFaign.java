package com.johanncanon.springcloud.ms.items.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.johanncanon.springcloud.ms.items.clients.ProductFeignClient;
import com.johanncanon.springcloud.ms.items.models.Item;

@Service
public class ItemServiceFaign implements ItemService{

    private final ProductFeignClient productFeignClient;

    public ItemServiceFaign(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<Item> findAll() {
        return productFeignClient.findAll()
            .stream()
            .map(product ->  new Item(product, new Random().nextInt(10) +1 ))
            .toList();
    }

    @Override
    public Optional<Item> findById(Long id) {
        var product = productFeignClient.details(id);
        if( product == null ) return Optional.empty();
        return Optional.of( new Item( product, new Random().nextInt(10) +1 ));
    }

}
