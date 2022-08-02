package com.globits.da.service;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.utils.Error;

import java.util.UUID;

public interface ValidEmployeeService {
    Error validEmployee(EmployeeDto dto, UUID id);

    // validate province District commune
    Error validAddress(Employee etity);
}
