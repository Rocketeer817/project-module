package com.example.proxyservice.sessionTests;

import com.example.proxyservice.testModels.Address;
import com.example.proxyservice.testModels.Person;
import com.example.proxyservice.testModelsRepo.AddressRepository;
import com.example.proxyservice.testModelsRepo.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.engine.spi.SessionLazyDelegator;
import org.hibernate.internal.SessionImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class personTests {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Test
    @Transactional
    @Rollback(value = false)
    void testPerson(){
        Person person = new Person();
        person.setName("John Cena");
        //personRepo.save(person);

        Address address = new Address();
        address.setHouseNumber(112);
        address.setStreet("Main Street");
        address.setCity("New York");
        address.setZipCode(12345);

        address.setPerson(person);
        //addressRepo.save(address);

        //address.setPerson(person);

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        person.setAddresses(addressList);

        personRepo.save(person);

        System.out.println("Debug");


    }

    @Test
    @Transactional
    @Rollback(value = false)
    void deletePerson(){
        Person person = personRepo.findById(352l).get();
        personRepo.delete(person);
    }
}
