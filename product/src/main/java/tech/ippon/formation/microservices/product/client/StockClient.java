package tech.ippon.formation.microservices.product.client;

import feign.Param;
import feign.RequestLine;
import tech.ippon.formation.microservices.product.domain.Stock;

public interface StockClient {

  @RequestLine("GET /api/stocks?productId={productId}")
  Stock getStocksForProduct(@Param("productId") String productId);

}
