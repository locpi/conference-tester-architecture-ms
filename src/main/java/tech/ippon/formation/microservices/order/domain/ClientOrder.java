package tech.ippon.formation.microservices.order.domain;


import java.io.Serializable;

public class ClientOrder implements Serializable {

    private String idClient;

    private String idProduit;

    public ClientOrder(String idClient, String idProduit) {
        this.idClient = idClient;
        this.idProduit = idProduit;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }
}
