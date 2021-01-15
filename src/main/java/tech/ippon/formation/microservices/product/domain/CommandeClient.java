package tech.ippon.formation.microservices.product.domain;


import java.io.Serializable;

public class CommandeClient implements Serializable {

    private String idClient;

    private String idProduit;

    public CommandeClient(String idClient, String idProduit) {
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
