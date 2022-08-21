package com.udacity.jdnd.course3.critter.controller.schedule;

import com.udacity.jdnd.course3.critter.entity.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.schedule.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        return scheduleDTO;
    }

    private Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }
}
