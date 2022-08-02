package com.globits.da.service;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.utils.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    // add employee
    Response<EmployeeDto> add(EmployeeDto employeeDto);
    // edit employee
    Response<EmployeeDto> edit(UUID id, EmployeeDto employeeDto);
    //get all employee
    List<EmployeeDto> getAllEmployee();
    // delete employee
    Boolean deleteById(UUID id);
    // write file excel
    Boolean write( String path);
    // read  file excel
    List<EmployeeDto> read(String path);
    // import file excel
    Response<List<EmployeeDto>> importExcel(MultipartFile file);
    //export file excel
    ByteArrayInputStream exportExcel();
}