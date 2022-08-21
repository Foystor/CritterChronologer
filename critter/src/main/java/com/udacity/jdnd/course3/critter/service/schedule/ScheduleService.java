package com.udacity.jdnd.course3.critter.service.schedule;

import com.udacity.jdnd.course3.critter.entity.schedule.Schedule;
import com.udacity.jdnd.course3.critter.repository.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> list() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    public List<Schedule> findByPet(long petId) {
        return scheduleRepository.findByPetsId(petId);
    }

    public List<Schedule> findByEmployee(long employeeId) {
        return scheduleRepository.findByEmployeesId(employeeId);
    }

    public List<Schedule> findByCustomer(long customerId) {
        return scheduleRepository.findByPetsOwnerId(customerId);
    }
}
