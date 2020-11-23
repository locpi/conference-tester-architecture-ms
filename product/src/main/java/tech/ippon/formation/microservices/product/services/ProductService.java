package tech.ippon.formation.microservices.product.services;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import tech.ippon.formation.microservices.product.client.StockClient;
import tech.ippon.formation.microservices.product.domain.Product;

@Service
public class ProductService {

  private static final List<Product> PRODUCTS =
      Arrays.asList(
          new Product(UUID.randomUUID().toString(), "clé à molette", "Une clé à molette molle"),
          new Product(UUID.randomUUID().toString(), "tournevis", "Un tournevis vicieux"),
          new Product(UUID.randomUUID().toString(), "escabeau", "Un escabeau moche escamotable"));

  private final StockClient stockClient;

  public ProductService(StockClient stockClient) {
    this.stockClient = stockClient;
  }

  public List<Product> getAllProducts() {
    return PRODUCTS.stream()
        .map(product -> product.setStock(this.stockClient.getStocksForProduct(product.getId()).getStock()))
        .collect(Collectors.toList());
  }
}
