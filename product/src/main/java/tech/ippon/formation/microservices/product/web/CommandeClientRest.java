package tech.ippon.formation.microservices.product.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ippon.formation.microservices.product.commande.CommandeClientService;
import tech.ippon.formation.microservices.product.domain.CommandeClient;

@RestController
@RequestMapping("/api/commandes")
public class CommandeClientRest {

    private final CommandeClientService commandeClientService;

    public CommandeClientRest(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @PostMapping
    public void creerCommande(@RequestBody CommandeClient commandeClient){
        commandeClientService.creerCommande(commandeClient);
    }

}
