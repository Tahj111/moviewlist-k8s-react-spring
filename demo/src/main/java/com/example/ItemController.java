package com.example.myspringcontainer.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    // In-memory storage for simplicity
    private final Map<Integer, String> items = new HashMap<>();
    private int currentId = 1;

    // Create a new item
    @PostMapping
    public String createItem(@RequestBody String name) {
        items.put(currentId, name);
        return "Created item with ID " + (currentId++);
    }

    // Get all items
    @GetMapping
    public Map<Integer, String> getAllItems() {
        return items;
    }

    // Get a single item by ID
    @GetMapping("/{id}")
    public String getItem(@PathVariable int id) {
        return items.getOrDefault(id, "Item not found");
    }

    // Update an item by ID
    @PutMapping("/{id}")
    public String updateItem(@PathVariable int id, @RequestBody String name) {
        if (items.containsKey(id)) {
            items.put(id, name);
            return "Updated item with ID " + id;
        } else {
            return "Item not found";
        }
    }

    // Delete an item by ID
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        if (items.containsKey(id)) {
            items.remove(id);
            return "Deleted item with ID " + id;
        } else {
            return "Item not found";
        }
    }
}



