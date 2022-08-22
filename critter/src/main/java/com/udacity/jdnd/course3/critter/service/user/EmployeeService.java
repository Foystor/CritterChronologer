package com.udacity.jdnd.course3.critter.service.user;

import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.repository.user.EmployeeRepository;
import com.udacity.jdnd.course3.critter.entity.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(long employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        employeeRepository.findById(employeeId).ifPresent(employee -> employee.setDaysAvailable(daysAvailable));
    }

    public List<Employee> findByDaysAvailableAndSkills(Employee employee) {
        List<Employee> employees =
                employeeRepository.findAllByDaysAvailableContaining(employee.getDaysAvailable().stream().findFirst().get());
        List<Employee> availableEmployees = new ArrayList<>();

        for (Employee e : employees) {
            // check if employees skills contains the required skills
            if (e.getSkills().containsAll(employee.getSkills())) availableEmployees.add(e);
        }

        return availableEmployees;
    }
}
