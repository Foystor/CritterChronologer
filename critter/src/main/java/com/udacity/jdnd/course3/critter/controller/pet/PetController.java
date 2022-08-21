package com.udacity.jdnd.course3.critter.controller.pet;

import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import com.udacity.jdnd.course3.critter.entity.user.Customer;
import com.udacity.jdnd.course3.critter.dto.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.pet.PetService;
import com.udacity.jdnd.course3.critter.service.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertEntityToPetDTO(petService.save(convertPetDTOToEntity(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertEntityToPetDTO(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.list().stream()
                .map(this::convertEntityToPetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.findByOwner(ownerId).stream()
                .map(this::convertEntityToPetDTO)
                .collect(Collectors.toList());
    }

    private PetDTO convertEntityToPetDTO(Pet pet) {
        if (pet == null) return null;
        else {
            PetDTO petDTO = new PetDTO();
            BeanUtils.copyProperties(pet, petDTO);
            petDTO.setOwnerId(pet.getOwner().getId());
            return petDTO;
        }
    }

    private Pet convertPetDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        Long ownerId = petDTO.getOwnerId();
        Customer customer = customerService.findById(ownerId);
        pet.setOwner(customer);

        return pet;
    }
}
