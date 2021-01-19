package tech.ippon.formation.microservices.order.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ippon.formation.microservices.order.service.ClientOrderService;
import tech.ippon.formation.microservices.order.domain.ClientOrder;

@RestController
@RequestMapping("/api/orders")
public class ClientOrderController {

    private final ClientOrderService clientOrderService;

    public ClientOrderController(ClientOrderService clientOrderService) {
        this.clientOrderService = clientOrderService;
    }

    @PostMapping
    public void creerCommande(@RequestBody ClientOrder clientOrder){
        clientOrderService.createNewCommande(clientOrder);
    }

}
