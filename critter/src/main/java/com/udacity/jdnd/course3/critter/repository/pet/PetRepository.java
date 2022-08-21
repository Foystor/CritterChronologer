package com.udacity.jdnd.course3.critter.repository.pet;

import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findByOwnerId(Long ownerId);
}
