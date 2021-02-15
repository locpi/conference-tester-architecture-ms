package tech.ippon.formation.microservices.order.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tech.ippon.formation.microservices.order.domain.ClientOrder;
import tech.ippon.formation.microservices.order.web.ClientOrderController;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ClientOrderServiceTest {



    @Autowired
    private ClientOrderController clientOrderController;

    @Test
    @Order(1)
    public void testCreateNewOrder(){
        ClientOrder clientOrder = new ClientOrder("client1","produit1");

    }
}