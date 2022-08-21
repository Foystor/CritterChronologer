package com.udacity.jdnd.course3.critter.controller.schedule;

import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import com.udacity.jdnd.course3.critter.entity.schedule.Schedule;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.dto.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.pet.PetService;
import com.udacity.jdnd.course3.critter.service.schedule.ScheduleService;
import com.udacity.jdnd.course3.critter.service.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertEntityToScheduleDTO(scheduleService.save(convertScheduleDTOToEntity(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.list().stream()
                .map(this::convertEntityToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findByPet(petId).stream()
                .map(this::convertEntityToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.findByEmployee(employeeId).stream()
                .map(this::convertEntityToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.findByCustomer(customerId).stream()
                .map(this::convertEntityToScheduleDTO)
                .collect(Collectors.toList());
    }

    private ScheduleDTO convertEntityToScheduleDTO(Schedule schedule) {
        if (schedule == null) return null;
        else {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);
            List<Long> employeeIds = new ArrayList<>();
            List<Employee> employees = schedule.getEmployees();
            if (employees != null) {
                for (Employee e : employees) {
                    employeeIds.add(e.getId());
                    scheduleDTO.setEmployeeIds(employeeIds);
                }
            }
            List<Long> petIds = new ArrayList<>();
            List<Pet> pets = schedule.getPets();
            if (pets != null) {
                for (Pet p : pets) {
                    petIds.add(p.getId());
                    scheduleDTO.setPetIds(petIds);
                }
            }
            return scheduleDTO;
        }
    }

    private Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Employee> employees = new ArrayList<>();
        if (employeeIds != null) {
            for (Long l : employeeIds) {
                employees.add(employeeService.findById(l));
            }
            schedule.setEmployees(employees);
        }
        List<Long> petIds = scheduleDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();
        if (petIds != null) {
            for (Long l : petIds) {
                pets.add(petService.findById(l));
            }
            schedule.setPets(pets);
        }
        return schedule;
    }
}
