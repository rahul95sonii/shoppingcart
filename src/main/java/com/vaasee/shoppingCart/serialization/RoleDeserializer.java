package com.vaasee.shoppingCart.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.vaasee.shoppingCart.dto.Role;

/*
 * 
 * author rahul.soni
 * 
 * */

public class RoleDeserializer extends JsonDeserializer<Role> {

    @Override
    public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().toUpperCase(); // Convert input to uppercase
        return Role.valueOf(value); // Convert to enum
    }
}