package com.johanncanon.springcloud.ms.items.services;

import java.util.List;
import java.util.Optional;

import com.johanncanon.springcloud.ms.items.models.Item;

public interface ItemService {

    List<Item> findAll();
    Optional<Item> findById( Long id );

}
