package com.productInventory.services;

import com.productInventory.entities.Customer;
import com.productInventory.exceptions.BadDataResponse;
import com.productInventory.exceptions.DataIntegrityViolation;
import com.productInventory.exceptions.ServiceUnavailable;
import com.productInventory.exceptions.ResourceNotFound;
import com.productInventory.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService {

  List<Customer> customers = new ArrayList<>();

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public List<Customer> queryCustomers(Customer customer) {
    try {
      if(customer.isEmpty()) {
        return customerRepository.findAll();
      } else {
        Example<Customer> CustomerExample = Example.of(customer);
        return customerRepository.findAll(CustomerExample);
      }
    } catch (Exception e) {
      throw new ServiceUnavailable(e);
    }
  }

  @Override
  public Customer getCustomer(Long id) {
    try {
      Customer customer = customerRepository.findById(id).orElse(null);

      if(customer != null) {
        return customer;
      }
    } catch (Exception e) {
      throw new ServiceUnavailable(e);
    }
    throw new ResourceNotFound("Could not locate a Customer with the id: " + id);
  }

  @Override
  public Customer addCustomer(Customer customer) {
    try {
      return customerRepository.save(customer);
    } catch (ServiceUnavailable e) {
      throw new ServiceUnavailable(e.getMessage());
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolation(e);
    }
  }

  @Override
  public Customer updateCustomerById(Long id, Customer customer) {
    if(!customer.getId().equals(id)) {
      throw new BadDataResponse("Customer ID must match the ID specified in the URL");
    }
    try {
      Customer CustomerFromDb = customerRepository.findById(id).orElse(null);

      if(CustomerFromDb != null) {
        return customerRepository.save(customer);
      }
    } catch (ServiceUnavailable e) {
      throw new ServiceUnavailable(e.getMessage());
    } catch (DataIntegrityViolation e) {
      throw new DataIntegrityViolation(e);
    }
    throw new ResourceNotFound("Could not locate a Customer with the id: " + id);
  }

  @Override
  public void deleteCustomer(Long id) {
    try {
      if(customerRepository.existsById(id)) {
        customerRepository.deleteById(id);
        return;
      }
    } catch (Exception e) {
      throw new ServiceUnavailable(e);
    }
    throw new ResourceNotFound("Could not locate a Customer with the id: " + id);
  }

  @Override
  public List<Customer> addCustomers(List<Customer> customers) {
    try {
      return customerRepository.saveAll(customers);
    } catch (Exception e) {
      throw new ServiceUnavailable(e);
    }
  }
}
