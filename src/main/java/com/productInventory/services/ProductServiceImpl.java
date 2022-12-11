package com.productInventory.services;

import com.productInventory.entities.Customer;
import com.productInventory.entities.Product;
import com.productInventory.exceptions.ResourceNotFound;
import com.productInventory.exceptions.ServiceUnavailable;
import com.productInventory.repository.ProductRepository;
import java.rmi.ServerError;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getProducts(Product product) {
    try {
      Example<Product> ProductExample = Example.of(product);
      return productRepository.findAll(ProductExample);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }
  }

  @Override
  public Product getProductById(Long id) {
    try {
      Product product = productRepository.findById(id).orElse(null);
      if(product != null) {
        return product;
      } else {
        logger.info("Get by id failed, it does not exist in the database: " + id);
        throw new ResourceNotFound("Get by id failed, it does not exist in the database: " + id);
      }
    } catch(ServiceUnavailable e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }
  }

}
