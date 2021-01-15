package tech.ippon.formation.microservices.product.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

  private String stockUrl;
  private String brokerUrl;

  public String getStockUrl() {
    return stockUrl;
  }

  public String getBrokerUrl() {
    return brokerUrl;
  }

  public ApplicationProperties setStockUrl(String stockUrl) {
    this.stockUrl = stockUrl;
    return this;
  }

  public ApplicationProperties setBrokerUrl(String brokerUrl) {
    this.brokerUrl = brokerUrl;
    return this;
  }
}
