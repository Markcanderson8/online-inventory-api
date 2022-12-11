package com.productInventory.services;

import com.productInventory.entities.Customer;
import java.util.List;

public interface CustomerService {
  List<Customer> queryCustomers(Customer customer);

  Customer getCustomer(Long id);

  Customer addCustomer(Customer customer);

  Customer updateCustomerById(Long id, Customer customer);

  void deleteCustomer(Long id);

  List<Customer> addCustomers(List<Customer> customers);
}
