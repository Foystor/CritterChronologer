package com.udacity.jdnd.course3.critter.repository.user;

import com.udacity.jdnd.course3.critter.entity.user.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByPetsId(long petId);
}
