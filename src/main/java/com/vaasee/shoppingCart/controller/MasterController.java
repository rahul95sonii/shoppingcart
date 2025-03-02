package com.vaasee.shoppingCart.controller;


import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 
 * author rahul.soni
 * 
 * */

@RestController
@RequestMapping("/master")
public class MasterController extends BaseController {

    // Handling the category request with validation
    @GetMapping("/category")
    public ResponseEntity<Object> getCategory() {
        // Check for validation errors
      

        // Sample categories (this is your actual business logic)
        HashMap<Integer, String> cat = new HashMap<>();
        cat.put(1, "Men");
        cat.put(2, "Women");

        // Check if the categories are empty
        if (cat.isEmpty()) {
            return sendErrorResponse("Category list is empty!"); // If categories are empty, send error response
        }

        // Return categories if everything is fine
        return sendSuccessResponse("Category list fetched successfully", cat); // Send categories with success response
    }
}


