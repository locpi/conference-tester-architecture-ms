package tech.ippon.formation.microservices.product.historiqueCommande.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tech.ippon.formation.microservices.product.historiqueCommande.domain.HistoriqueCommandeProduitClient;

@Service
public class CommandeClientRedisService {

    private final CommandeclientRedisRepository commandeclientRedisRepository;


    public CommandeClientRedisService(
        CommandeclientRedisRepository commandeclientRedisRepository) {
        this.commandeclientRedisRepository = commandeclientRedisRepository;
    }

    public void save(HistoriqueCommandeProduitClient historiqueCommandeProduitClient){
        historiqueCommandeProduitClient.setId("id-"+historiqueCommandeProduitClient.getIdClient());
        this.commandeclientRedisRepository.save(historiqueCommandeProduitClient);
    }

    public HistoriqueCommandeProduitClient getById(String id){
        return commandeclientRedisRepository.findById(id).orElseThrow(()-> new RuntimeException("Pas d'historique avec cet id : "+id));
    }

}
