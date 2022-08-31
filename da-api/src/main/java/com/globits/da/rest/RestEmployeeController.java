package com.globits.da.rest;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.service.EmployeeService;
import com.globits.da.utils.Response;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
public class RestEmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Response<EmployeeDto> save(@RequestBody EmployeeDto dto){
        return employeeService.add(dto);
    }

    @PutMapping("/{id}")
    public Response<EmployeeDto> update(@PathVariable UUID id, @RequestBody EmployeeDto dto){
        return employeeService.edit(id,dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id){
        return new ResponseEntity<>(employeeService.deleteById(id), HttpStatus.OK);
    }
    @GetMapping("getList")
    public ResponseEntity<List<EmployeeDto>> getList(){
        return new ResponseEntity<>(employeeService.getAllEmployee(),HttpStatus.OK);
    }
    @GetMapping("/export")
    public boolean exportFile(HttpServletResponse response) throws IOException {
        ByteArrayInputStream byteArrayInputStream = employeeService.exportExcel();
        if (byteArrayInputStream == null){
            return false;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=Employee.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
        return true;
    }
    @GetMapping("/import")
    public Response<List<EmployeeDto>> importFile(@RequestBody MultipartFile file){
        return employeeService.importExcel(file);
    }
}
