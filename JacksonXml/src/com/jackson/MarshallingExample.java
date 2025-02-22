package com.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MarshallingExample {
	public static void main(String[] args) throws Exception {
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a User object
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        // Convert User object to JSON
        String jsonString = objectMapper.writeValueAsString(user);
        System.out.println("JSON String: " + jsonString);
    }
}