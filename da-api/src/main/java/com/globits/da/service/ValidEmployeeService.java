package com.globits.da.service;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.utils.Error;

public interface ValidEmployeeService {
    Error validEmployee(EmployeeDto dto);

    Error validAddress(EmployeeDto dto);
}
