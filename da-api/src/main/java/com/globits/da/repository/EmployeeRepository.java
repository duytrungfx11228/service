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

    @Query("select new com.globits.da.dto.EmployeeDto(employee) from Employee employee")
    List<EmployeeDto> getListEmployee();

    @Query("select count(entity.id) from Employee entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);
}
