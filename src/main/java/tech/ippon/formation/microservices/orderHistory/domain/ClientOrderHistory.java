package tech.ippon.formation.microservices.orderHistory.domain;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class ClientOrderHistory {

    @Id
    private String id;

    private String idClient;

    private String idProduit;

    private Date dateReception = new Date();

    public ClientOrderHistory( String idClient, String idProduit) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateReception() {
        return dateReception;
    }
}
