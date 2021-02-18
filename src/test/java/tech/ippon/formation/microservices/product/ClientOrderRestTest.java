package tech.ippon.formation.microservices.product;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.broker.BrokerService;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import tech.ippon.formation.microservices.order.domain.ClientOrder;
import tech.ippon.formation.microservices.orderHistory.service.ClientOrderHistoryService;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ClientOrderRestTest {


    private static final String BROKER_ACTIVE_MQ = "tcp://localhost:3333";

    public static final String API_ORDERS = "/api/orders";

    private static BrokerService broker = new BrokerService();

    @Rule
    static GenericContainer redis = new GenericContainer("redis:3.0.6");

    @Autowired
    private ClientOrderHistoryService commandeClientRedisService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void before() throws Exception {
        redis.setPortBindings(Arrays.asList("6379:6379"));
        broker.addConnector(BROKER_ACTIVE_MQ);
        broker.start();
        redis.start();
    }

    @Test
    public void testCreationCommandClientIsOK() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ((Runnable) () -> {
            ClientOrder co = new ClientOrder("client1", "produit1");
            try {
                mockMvc.perform(
                        post(API_ORDERS)
                                .content(asJsonString(co))
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
            } catch (Exception e) {
                fail();
            }
            try {
                countDownLatch.await(5,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).run();

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