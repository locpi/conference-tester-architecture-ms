package tech.ippon.formation.microservices.orderHistory.service;

import org.springframework.stereotype.Service;
import tech.ippon.formation.microservices.orderHistory.domain.ClientOrderHistory;
import tech.ippon.formation.microservices.orderHistory.repository.ClientOrderHistoryRepository;

@Service
public class ClientOrderHistoryService {

    private final ClientOrderHistoryRepository clientOrderHistoryRepository;


    public ClientOrderHistoryService(
        ClientOrderHistoryRepository clientOrderHistoryRepository) {
        this.clientOrderHistoryRepository = clientOrderHistoryRepository;
    }

    public void save(ClientOrderHistory clientOrderHistory){
        clientOrderHistory.setId("id-"+ clientOrderHistory.getIdClient());
        this.clientOrderHistoryRepository.save(clientOrderHistory);
    }

    public ClientOrderHistory getById(String id){
        return clientOrderHistoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Pas d'historique avec cet id : "+id));
    }

}
