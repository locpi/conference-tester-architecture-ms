package tech.ippon.formation.microservices.product;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.broker.BrokerService;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import tech.ippon.formation.microservices.order.domain.ClientOrder;
import tech.ippon.formation.microservices.orderHistory.service.ClientOrderHistoryService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ClientOrderRestTest {

    /**
     * URL connexion à active MQ
     */
    private static final String BROKER_ACTIVE_MQ = "tcp://localhost:3333";
    public static final String API_ORDERS = "/api/orders";

    /**
     * BROKER Active MQ Embeded
     */
    private static BrokerService broker = new BrokerService();


    /**
     * Container docker à lancer pour le test
     */
    @Rule
    static GenericContainer redis = new GenericContainer("redis:3.0.6");

    @Autowired
    private ClientOrderHistoryService commandeClientRedisService;

    @Autowired
    private MockMvc mockMvc;


    @BeforeAll
    public static void before() throws Exception {
        redis.start();
        broker.addConnector(BROKER_ACTIVE_MQ);
        broker.start();
    }

    @Test
    public void testCreationCommandClientIsOK() throws Exception {
        ClientOrder co = new ClientOrder("client1", "produit1");
        this.mockMvc.perform(
                post(API_ORDERS)
                        .content(asJsonString(co))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        broker.checkQueueSize("client_order");
        Assertions.assertNotNull(commandeClientRedisService.getById("id-client1"));
    }

    @AfterAll
    public static void after() throws Exception {
        redis.stop();
        broker.stop();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}