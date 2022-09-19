package com.globits.da.service;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.utils.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Response<EmployeeDto> add(EmployeeDto employeeDto);

    Response<EmployeeDto> edit(UUID id, EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployee();

    boolean deleteById(UUID id);

    Response<List<EmployeeDto>> importExcel(MultipartFile file);

    ByteArrayInputStream exportExcel();
}
