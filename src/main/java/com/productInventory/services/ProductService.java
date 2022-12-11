package com.productInventory.services;

import com.productInventory.entities.Product;
import java.util.List;

public interface ProductService {

  List<Product> getProducts(Product product);

  Product getProductById(Long id);
}
