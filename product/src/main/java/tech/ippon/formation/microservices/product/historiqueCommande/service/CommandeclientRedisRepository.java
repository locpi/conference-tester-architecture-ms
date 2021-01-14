package tech.ippon.formation.microservices.product.historiqueCommande.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.ippon.formation.microservices.product.historiqueCommande.domain.HistoriqueCommandeProduitClient;

@Repository
public interface CommandeclientRedisRepository extends CrudRepository<HistoriqueCommandeProduitClient,String> {

}
