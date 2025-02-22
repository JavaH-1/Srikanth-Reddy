package com.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

public class MarshallingFileExample {
    public static void main(String[] args) {
        try {
            
            File file = new File("src\\main\\resources\\student.xml");

            
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean dirsCreated = parentDir.mkdirs();
                if (dirsCreated) {
                    System.out.println("Created directories: " + parentDir.getAbsolutePath());
                }
            }

            // Create a User object
            User user = new User();
            user.setId(1);
            user.setName("John Doe");
            user.setEmail("john.doe@example.com");

            // Create XmlMapper (ObjectMapper for XML)
            XmlMapper xmlMapper = new XmlMapper();

            // Convert User object to XML file (Marshalling)
            xmlMapper.writeValue(file, user);
            System.out.println("XML written to file: " + file.getAbsolutePath());

            // Read XML file back to User object (Unmarshalling)
            User unmarshalledUser = xmlMapper.readValue(file, User.class);
            System.out.println("\nUnmarshalled User Object:");
            System.out.println(unmarshalledUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}