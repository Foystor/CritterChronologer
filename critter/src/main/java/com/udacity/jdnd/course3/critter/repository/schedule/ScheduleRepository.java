package com.udacity.jdnd.course3.critter.repository.schedule;

import com.udacity.jdnd.course3.critter.entity.schedule.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findByPetsId(long petId);
    List<Schedule> findByEmployeesId(long employeeId);
    List<Schedule> findByPetsOwnerId(long customerId);
}
