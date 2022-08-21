package com.udacity.jdnd.course3.critter.controller.user;

import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import com.udacity.jdnd.course3.critter.entity.user.Customer;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.service.pet.PetService;
import com.udacity.jdnd.course3.critter.service.user.CustomerService;
import com.udacity.jdnd.course3.critter.service.user.EmployeeService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return convertEntityToCustomerDTO(customerService.save(convertCustomerDTOToEntity(customerDTO)));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.list().stream()
                .map(this::convertEntityToCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertEntityToCustomerDTO(customerService.findByPet(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return convertEntityToEmployeeDTO(employeeService.save(convertEmployeeDTOToEntity(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEntityToEmployeeDTO(employeeService.findById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return employeeService.findByDaysAvailableAndSkills(convertEmployeeRequestDTOToEntity(employeeDTO))
                .stream()
                .map(this::convertEntityToEmployeeDTO)
                .collect(Collectors.toList());
    }

    private CustomerDTO convertEntityToCustomerDTO(Customer customer) {
        if (customer == null) return null;
        else {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customer, customerDTO);
            // covert list of Pets to list of PetIds
            List<Long> petIds = new ArrayList<>();
            List<Pet> pets = customer.getPets();
            if (pets != null) {
                for (Pet p : pets) {
                    petIds.add(p.getId());
                }
                customerDTO.setPetIds(petIds);
            }
            return customerDTO;
        }
    }

    private Customer convertCustomerDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        List<Long> petIds = customerDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();
        if (petIds != null) {
            for (Long l : petIds) {
                pets.add(petService.findById(l));
            }
            customer.setPets(pets);
        }
        return customer;
    }

    private EmployeeDTO convertEntityToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private Employee convertEmployeeRequestDTOToEntity(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDTO, employee);
        DayOfWeek dayOfWeek = employeeRequestDTO.getDate().getDayOfWeek();
        Set<DayOfWeek> daysAvailable = new HashSet<>();
        daysAvailable.add(dayOfWeek);
        employee.setDaysAvailable(daysAvailable);
        return employee;
    }
}
