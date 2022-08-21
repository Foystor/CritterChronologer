package com.udacity.jdnd.course3.critter.repository.user;

import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.entity.user.EmployeeSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAllByDaysAvailableInAndSkillsIn(Set<DayOfWeek> daysAvailable, Set<EmployeeSkill> skills);
}
