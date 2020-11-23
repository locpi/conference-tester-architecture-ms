package tech.ippon.formation.microservices.product.domain;

public class Stock {

  private int stock;

  public int getStock() {
    return stock;
  }

  public Stock setStock(int stock) {
    this.stock = stock;
    return this;
  }
}
