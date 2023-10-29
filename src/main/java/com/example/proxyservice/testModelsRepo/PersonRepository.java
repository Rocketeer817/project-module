package com.example.proxyservice.testModelsRepo;

import com.example.proxyservice.testModels.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
