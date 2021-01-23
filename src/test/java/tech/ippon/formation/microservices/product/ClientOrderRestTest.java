package tech.ippon.formation.microservices.product;


import org.apache.activemq.broker.BrokerService;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import tech.ippon.formation.microservices.order.domain.ClientOrder;
import tech.ippon.formation.microservices.orderHistory.service.ClientOrderHistoryService;
import tech.ippon.formation.microservices.order.web.ClientOrderController;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles("test")
class ClientOrderRestTest {

    /**
     * URL connexion à active MQ
     */
    private static final String BROKER_ACTIVE_MQ = "tcp://localhost:3333";

    /**
     * BROKER Active MQ Embeded
     */
    private static BrokerService broker = new BrokerService();


    /**
     * Container docker à lancer pour le test
     */
    @Rule
    static GenericContainer redis = new GenericContainer("redis:3.0.6");

    /**
     * Composant à tester
     */
    @Autowired
    private ClientOrderController clientOrderController;

    /**
     * Composant à tester
     */
    @Autowired
    private ClientOrderHistoryService commandeClientRedisService;

    @BeforeAll
    public static void before() throws Exception {
        redis.setPortBindings(Arrays.asList("10874:6379"));
        redis.start();
        broker.addConnector(BROKER_ACTIVE_MQ);
        broker.start();
    }

    @AfterAll
    public static void after() throws Exception {
        redis.stop();
        broker.stop();
    }

    @Test
    @Order(1)
    public void test1() throws InterruptedException {
        ClientOrder co = new ClientOrder("client1","produit1");
        clientOrderController.creerCommande(co);
        Thread.sleep(5000);
    }

    @Test
    @Order(2)
    public void test2() throws Exception {
        Assertions.assertNotNull(commandeClientRedisService.getById("id-client1"));
    }

}