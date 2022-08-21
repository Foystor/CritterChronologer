package com.udacity.jdnd.course3.critter.service.user;

import com.udacity.jdnd.course3.critter.entity.user.Customer;
import com.udacity.jdnd.course3.critter.repository.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).get();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> list() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer findByPet(long petId) {
        return customerRepository.findByPetsId(petId);
    }
}
