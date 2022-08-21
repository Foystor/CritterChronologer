package com.udacity.jdnd.course3.critter.service.pet;

import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import com.udacity.jdnd.course3.critter.entity.user.Customer;
import com.udacity.jdnd.course3.critter.repository.pet.PetRepository;
import com.udacity.jdnd.course3.critter.repository.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Pet save(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getOwner();

        List<Pet> customerPets = customer.getPets();

        if (customerPets == null) {
            customerPets = new ArrayList<>();
        }

        customerPets.add(savedPet);
        customer.setPets(customerPets);
        customerRepository.save(customer);

        return savedPet;
    }

    public Pet findById(long petId) {
        return petRepository.findById(petId).get();
    }

    public List<Pet> list() {
        return (List<Pet>) petRepository.findAll();
    }

    public List<Pet> findByOwner(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }
}
