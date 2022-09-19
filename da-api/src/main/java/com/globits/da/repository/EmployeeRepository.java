package com.globits.da.repository;

import com.globits.da.domain.Employee;

import com.globits.da.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query("SELECT new com.globits.da.dto.EmployeeDto(employee) FROM Employee employee")
    List<EmployeeDto> getListEmployee();

    @Query("SELECT COUNT(entity.id) FROM Employee entity WHERE entity.code =?1 ")
    long countAllByCode(String code);
}
