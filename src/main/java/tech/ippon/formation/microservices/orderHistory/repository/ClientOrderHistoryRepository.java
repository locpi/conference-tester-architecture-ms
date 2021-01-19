package tech.ippon.formation.microservices.orderHistory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.ippon.formation.microservices.orderHistory.domain.ClientOrderHistory;

@Repository
public interface ClientOrderHistoryRepository extends CrudRepository<ClientOrderHistory,String> {

}
