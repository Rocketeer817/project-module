package com.example.proxyservice.testModelsRepo;

import com.example.proxyservice.testModels.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
