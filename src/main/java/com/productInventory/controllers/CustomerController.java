package com.productInventory.controllers;

import static com.productInventory.constants.StringConstants.CONTEXT_CUSTOMERS;
import static com.productInventory.constants.StringConstants.LOGGER_DELETE_REQUEST_RECEIVED;
import static com.productInventory.constants.StringConstants.LOGGER_POST_REQUEST_RECEIVED;
import static com.productInventory.constants.StringConstants.LOGGER_PUT_REQUEST_RECEIVED;
import static com.productInventory.constants.StringConstants.LOGGER_REQUEST_RECEIVED;

import com.productInventory.entities.Customer;
import com.productInventory.services.CustomerService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONTEXT_CUSTOMERS)
public class CustomerController {
  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<Customer>> queryCustomers(Customer customer) {
    logger.info(new Date() + LOGGER_REQUEST_RECEIVED + customer.toString());
    return new ResponseEntity<>(customerService.queryCustomers(customer), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    logger.info(new Date() + LOGGER_REQUEST_RECEIVED + id);

    return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
  }

  @PostMapping(value = "/all")
  public ResponseEntity<List<Customer>> saveAll(@Valid @RequestBody List<Customer> customers) {
    logger.info(new Date() + LOGGER_POST_REQUEST_RECEIVED);

    return new ResponseEntity<>(customerService.addCustomers(customers), HttpStatus.CREATED);
  }

  @PostMapping
  public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer) {
    logger.info(new Date() + LOGGER_POST_REQUEST_RECEIVED);

    return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id, @Valid @RequestBody Customer customer) {
    logger.info(new Date() + LOGGER_PUT_REQUEST_RECEIVED + id);

    return new ResponseEntity<>(customerService.updateCustomerById(id, customer), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable Long id) {
    logger.info(new Date() + LOGGER_DELETE_REQUEST_RECEIVED + id);

    customerService.deleteCustomer(id);
  }
}
