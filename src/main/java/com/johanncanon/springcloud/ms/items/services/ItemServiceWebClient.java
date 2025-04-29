package com.johanncanon.springcloud.ms.items.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.johanncanon.springcloud.ms.items.models.Item;
import com.johanncanon.springcloud.ms.items.models.Product;

@Primary
@Service
public class ItemServiceWebClient implements ItemService{

    private final WebClient.Builder webClient;

    public ItemServiceWebClient( Builder webClient ) {
        this.webClient = webClient;
    }

    @Override
    public List<Item> findAll() {
        return webClient.build()
                .get()
                .uri("/all-products")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class)
                    .map(product ->  new Item(product, new Random().nextInt(10) +1 ))
                .collectList()
                .block();
    }

    @Override
    public Optional<Item> findById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        try {
            return Optional.ofNullable(
                webClient.build()
                    .get()
                    .uri( "/{id}", params )
                    .accept( MediaType.APPLICATION_JSON )
                    .retrieve()
                    .bodyToMono( Product.class )
                        .map( product -> new Item( product, new Random().nextInt(10) +1 ) )
                    .block()
        );
        } catch (WebClientResponseException e) {
            return Optional.empty();
        }
        
    }

}
