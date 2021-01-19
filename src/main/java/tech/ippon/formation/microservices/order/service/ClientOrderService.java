package tech.ippon.formation.microservices.order.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tech.ippon.formation.microservices.order.domain.ClientOrder;

@Service
public class ClientOrderService {

    private static final String TOPIC = "client_order";

    private JmsTemplate jmsTemplate;

    public ClientOrderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void createNewCommande(ClientOrder clientOrder){
        try{
            jmsTemplate.convertAndSend(TOPIC, clientOrder);
        } catch(Exception e){
            e.printStackTrace();
       }
    }

}
