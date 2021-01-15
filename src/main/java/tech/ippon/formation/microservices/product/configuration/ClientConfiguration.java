package tech.ippon.formation.microservices.product.configuration;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ippon.formation.microservices.product.client.StockClient;

@Configuration
public class ClientConfiguration {

  private final ApplicationProperties applicationProperties;

  public ClientConfiguration(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  @Bean(name = "stockClient")
  public StockClient stockClient() {
    return new Feign.Builder()
        .decoder(new GsonDecoder())
        .target(StockClient.class, this.applicationProperties.getStockUrl());
  }
}
