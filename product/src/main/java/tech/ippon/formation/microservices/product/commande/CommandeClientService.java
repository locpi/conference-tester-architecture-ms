package tech.ippon.formation.microservices.product.commande;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tech.ippon.formation.microservices.product.domain.CommandeClient;

@Service
public class CommandeClientService {

    private static final String TOPIC = "commande_client";

    private JmsTemplate jmsTemplate;

    public CommandeClientService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void creerCommande(CommandeClient commandeClient){
        try{
            jmsTemplate.convertAndSend(TOPIC,commandeClient);
        } catch(Exception e){
            e.printStackTrace();
       }
    }

}
