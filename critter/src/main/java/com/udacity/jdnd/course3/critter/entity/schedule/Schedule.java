package com.udacity.jdnd.course3.critter.entity.schedule;

import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.entity.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "schedule_employee",
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(
            name = "schedule_pet",
            inverseJoinColumns = {@JoinColumn(name = "pet_id")}
    )
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "schedule_activity")
    @Column(name = "activity")
    private Set<EmployeeSkill> activities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
