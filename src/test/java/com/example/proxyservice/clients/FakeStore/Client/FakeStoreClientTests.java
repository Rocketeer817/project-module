package com.example.proxyservice.clients.FakeStore.Client;

import com.example.proxyservice.clients.FakeStore.client.FakeStoreProductClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FakeStoreClientTests {

    @Autowired
    public FakeStoreProductClient fakeStoreProductClient;

    @Test
    public void getAllProductsTest(){

    }


}
