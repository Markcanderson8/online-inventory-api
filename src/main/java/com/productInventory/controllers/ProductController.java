package com.productInventory.controllers;

import static com.productInventory.constants.StringConstants.CONTEXT_PRODUCTS;
import static com.productInventory.constants.StringConstants.LOGGER_REQUEST_RECEIVED;

import com.productInventory.entities.Product;
import com.productInventory.services.ProductService;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import javax.print.attribute.standard.DateTimeAtCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = CONTEXT_PRODUCTS)
public class ProductController {

  private final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<Product>> getProducts(Product product) {
    logger.info(new Date() + LOGGER_REQUEST_RECEIVED + product.toString());

    return new ResponseEntity<>(productService.getProducts(product), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    logger.info(new Date() + LOGGER_REQUEST_RECEIVED + id);

    return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
  }
}
