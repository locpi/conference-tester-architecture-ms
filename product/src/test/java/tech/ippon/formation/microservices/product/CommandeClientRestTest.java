package tech.ippon.formation.microservices.product;

import java.util.Arrays;
import org.apache.activemq.broker.BrokerService;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import tech.ippon.formation.microservices.product.domain.CommandeClient;
import tech.ippon.formation.microservices.product.historiqueCommande.service.CommandeClientRedisService;
import tech.ippon.formation.microservices.product.web.CommandeClientRest;

@SpringBootTest
@ActiveProfiles("test")
class CommandeClientRestTest {

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
    private CommandeClientRest commandeClientRest;

    /**
     * Composant à tester
     */
    @Autowired
    private CommandeClientRedisService commandeClientRedisService;

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
    public void testPourCreationCommandeOk(){
        CommandeClient co = new CommandeClient("client1","produit1");
        commandeClientRest.creerCommande(co);
        Assertions.assertNotNull(commandeClientRedisService.getById("id-client1"));
    }


}