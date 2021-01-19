package tech.ippon.formation.microservices.orderHistory.mq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tech.ippon.formation.microservices.order.domain.ClientOrder;
import tech.ippon.formation.microservices.orderHistory.domain.ClientOrderHistory;
import tech.ippon.formation.microservices.orderHistory.service.ClientOrderHistoryService;

@Component
public class NewOrderMessageListener implements MessageListener {

    private static final String TOPIC = "client_order";

    private ClientOrderHistoryService clientOrderHistoryService;

    public NewOrderMessageListener(
        ClientOrderHistoryService commandeClientRedisService) {
        this.clientOrderHistoryService = commandeClientRedisService;
    }

    @Override
    @JmsListener(destination = TOPIC)
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            ClientOrder employee = (ClientOrder)objectMessage.getObject();
            ClientOrderHistory clientOrderHistory = new ClientOrderHistory( employee.getIdClient(),employee.getIdProduit());
            clientOrderHistoryService.save(clientOrderHistory);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
