package tech.ippon.formation.microservices.product.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

  private String stockUrl;

  public String getStockUrl() {
    return stockUrl;
  }

  public ApplicationProperties setStockUrl(String stockUrl) {
    this.stockUrl = stockUrl;
    return this;
  }
}
