package com.marshal;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class MarshalClass {
    public static void main(String[] args) throws Exception {
        // Create a Person object
        Employee person = new Employee(1, "Aravind");

        // Marshalling
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(person, new File("Emp.xml"));
        System.out.println("XML written to Emp.xml");

        // Unmarshalling
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Employee unmarshalledEmp = (Employee) unmarshaller.unmarshal(new File("Emp.xml"));
        System.out.println("Unmarshalled: " + unmarshalledEmp.getEmpName() + ", " + unmarshalledEmp.getEmpId());
    }
}
