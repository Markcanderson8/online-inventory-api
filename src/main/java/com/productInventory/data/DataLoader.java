package com.productInventory.data;

import com.productInventory.entities.Address;
import com.productInventory.entities.Customer;
import com.productInventory.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

  @Autowired
  private CustomerRepository customerRepository;


  private Address a1 = new Address(1L, "801 crestview dr", "easley", "sc", 29642);
  private Address a2 = new Address(2L, "2319 freshwater dr", "easley", "sc", 29642);
  private Address a3 = new Address(3L, "900 crestview dr", "easley", "sc", 29642);
  private Address a4 = new Address(4L, "125 n main st", "easley", "sc", 29640);
  private Address a5 = new Address(5L, "125 s main st", "easley", "sc", 29640);

  private Customer customer1 = new Customer(1L, "mark", "anderson", "864-637-9485", "mark@gmail.com", a1);
  private Customer customer2 = new Customer(2L, "michael", "anderson", "864-243-1641", "michael@gmail.com", a2);
  private Customer customer3 = new Customer(3L, "debra", "anderson", "815-218-3311", "debra@gmail.com", a3);
  private Customer customer4 = new Customer(4L, "dale", "anderson", "815-218-4860", "dale@gmail.com", a4);
  private Customer customer5 = new Customer(5L, "madison", "anderson", "864-952-9902", "madison@gmail.com", a5);

  private List<Customer> customers = new ArrayList<>();

  @Override
  public void run(String... args) throws Exception {
    logger.info("Loading data...");

    loadCustomers();
  }

  private void loadCustomers() {
    customer1 = customerRepository.save(customer1);
    customer2 = customerRepository.save(customer2);
    customer3 = customerRepository.save(customer3);
    customer4 = customerRepository.save(customer4);
    customer5 = customerRepository.save(customer5);
  }
}
