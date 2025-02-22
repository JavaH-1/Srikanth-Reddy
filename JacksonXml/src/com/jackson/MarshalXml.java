package com.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class MarshalXml {

	    public static void main(String[] args) {
	        try {
	            // Create a User object
	            User user = new User();
	            user.setId(1);
	            user.setName("John Doe");
	            user.setEmail("john.doe@example.com");

	            // Create XmlMapper (ObjectMapper for XML)
	            XmlMapper xmlMapper = new XmlMapper();

	            // Convert User object to XML string (Marshalling)
	            String xmlString = xmlMapper.writeValueAsString(user);
	            
	            System.out.println("Marshalled XML:");
	            System.out.println(xmlString);

	            // Convert XML string back to User object (Unmarshalling)
	            User unmarshalledUser = xmlMapper.readValue(xmlString, User.class);
	            System.out.println("\nUnmarshalled User Object:");
	            System.out.println(unmarshalledUser);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

