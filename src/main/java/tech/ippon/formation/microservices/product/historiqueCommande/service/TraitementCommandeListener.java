package tech.ippon.formation.microservices.product.historiqueCommande.service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tech.ippon.formation.microservices.product.domain.CommandeClient;
import tech.ippon.formation.microservices.product.historiqueCommande.domain.HistoriqueCommandeProduitClient;

@Component
public class TraitementCommandeListener implements MessageListener {

    private static final String TOPIC = "commande_client";

    private CommandeClientRedisService commandeClientRedisService;

    public TraitementCommandeListener(
        CommandeClientRedisService commandeClientRedisService) {
        this.commandeClientRedisService = commandeClientRedisService;
    }

    @Override
    @JmsListener(destination = TOPIC)
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            CommandeClient employee = (CommandeClient)objectMessage.getObject();
            HistoriqueCommandeProduitClient historiqueCommandeProduitClient = new HistoriqueCommandeProduitClient( employee.getIdClient(),employee.getIdProduit());
            commandeClientRedisService.save(historiqueCommandeProduitClient);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
