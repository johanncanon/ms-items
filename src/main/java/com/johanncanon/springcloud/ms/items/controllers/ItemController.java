package com.johanncanon.springcloud.ms.items.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.johanncanon.springcloud.ms.items.models.Item;
import com.johanncanon.springcloud.ms.items.services.ItemService;

@RestController
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> list(){
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail( @PathVariable("id") Long id){
        var itemOptional = itemService.findById(id);
        if( itemOptional.isPresent() ) return ResponseEntity.ok( itemOptional.get() );
        return ResponseEntity.status(404)
            .body( Collections.singletonMap("message", "Item with Product ID: '" + id +"' does not exist.") );
    }


}
