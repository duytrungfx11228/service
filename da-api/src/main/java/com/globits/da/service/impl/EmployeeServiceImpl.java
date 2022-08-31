package com.globits.da.service.impl;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.ProvinceDto;

import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.EmployeeService;
import com.globits.da.service.ValidEmployeeService;
import com.globits.da.utils.Error;
import com.globits.da.utils.Response;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

import static com.globits.da.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private ProvinceRepository provinceRepos;
    @Autowired
    private DistrictRepository districtRepos;
    @Autowired
    private CommuneRepository communeRepos;
    @Autowired
    private ValidEmployeeService validEmployeeService;
    @Override
    public Response<EmployeeDto> add(EmployeeDto employeeDto) {
            Error error = validEmployeeService.validEmployee(employeeDto);
            if (error != Error.OK){
                return new Response<>(new EmployeeDto(),error);
            }
            Employee entity = new Employee();
            entity.setCode(employeeDto.getCode());
            entity.setName(employeeDto.getName());
            entity.setEmail(employeeDto.getEmail());
            entity.setPhone(employeeDto.getPhone());
            entity.setAge(employeeDto.getAge());
            if (employeeDto.getProvinceDto() != null && employeeDto.getProvinceDto().getId() != null){
                Province province =  provinceRepos.getOne(employeeDto.getProvinceDto().getId());
                entity.setProvince(province);
            }
            if (employeeDto.getDistrictDto() != null && employeeDto.getDistrictDto().getId() != null){
               District district = districtRepos.getOne(employeeDto.getDistrictDto().getId());
               entity.setDistrict(district);
            }
            if (employeeDto.getCommuneDto()!= null && employeeDto.getCommuneDto().getId() != null){
                Commune commune = communeRepos.getOne(employeeDto.getCommuneDto().getId());
                entity.setCommune(commune);
            }

            EmployeeDto dto = new EmployeeDto(repository.save(entity));
            return new Response<>(dto);
    }

    @Override
    public Response<EmployeeDto> edit(UUID id, EmployeeDto employeeDto) {
        if (id == null ){
            return new Response<>(new EmployeeDto());
        }
        Error error = validEmployeeService.validEmployee(employeeDto);
        if (error != Error.OK){
            return new Response<>(new EmployeeDto(),error);
        }
        Employee entity = repository.getOne(id);
        entity.setCode(employeeDto.getCode());
        entity.setName(employeeDto.getName());
        entity.setEmail(employeeDto.getEmail());
        entity.setPhone(employeeDto.getPhone());
        entity.setAge(employeeDto.getAge());
        if (employeeDto.getProvinceDto() != null && employeeDto.getDistrictDto() != null && employeeDto.getCommuneDto() != null){
                if ( employeeDto.getProvinceDto().getId() != null){
                   Province province = provinceRepos.getOne(employeeDto.getProvinceDto().getId());
                   entity.setProvince(province);
                }
                if (employeeDto.getDistrictDto().getId() != null){
                   District district = districtRepos.getOne(employeeDto.getDistrictDto().getId());
                   entity.setDistrict(district);
                }
                if ( employeeDto.getCommuneDto().getId() != null){
                   Commune commune = communeRepos.getOne(employeeDto.getCommuneDto().getId());
                   entity.setCommune(commune);
                }
        }

        EmployeeDto dto = new EmployeeDto(repository.save(entity));
        return new Response<>(dto);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return repository.getListEmployee();
    }

    @Override
    public boolean deleteById(UUID id) {
        if (id != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Response<List<EmployeeDto>> importExcel(MultipartFile file) {
        List<EmployeeDto> dtoList = new ArrayList<>();
        StringBuilder message = new StringBuilder("Error: ");

        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(SHEET_NAME);

            Iterator<Row> iterator = sheet.iterator();
            iterator.next();// row header
            DataFormatter fm = new DataFormatter();

            while (iterator.hasNext()){
                Row row = iterator.next();
                int rowNum = Integer.parseInt(fm.formatCellValue(row.getCell(5)));
                EmployeeDto dto = new EmployeeDto();
                dto.setCode(row.getCell(1).getStringCellValue());
                dto.setName(row.getCell(2).getStringCellValue());
                dto.setEmail(row.getCell(3).getStringCellValue());
                dto.setPhone(row.getCell(4).getStringCellValue());
                dto.setAge(Integer.parseInt(fm.formatCellValue(row.getCell(5))));
                dto.setProvinceDto(new ProvinceDto(UUID.fromString(fm.formatCellValue(row.getCell(6)))));
                dto.setDistrictDto(new DistrictDto(UUID.fromString(fm.formatCellValue(row.getCell(7)))));
                dto.setCommuneDto(new CommuneDto(UUID.fromString(fm.formatCellValue(row.getCell(8)))));
                if (validEmployeeService.validEmployee(dto) != Error.OK ){
                    message.append(rowNum).append(",");
                    continue;
                }
                add(dto);
                dtoList.add(dto);
            }
            inputStream.close();
            workbook.close();

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new Response<>(dtoList, message.toString());
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        List<EmployeeDto> list = repository.getListEmployee();
        ByteArrayOutputStream byteAOS = new ByteArrayOutputStream();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet =  workbook.createSheet(SHEET_NAME);
        int rowNum = 0;
        Cell cell;
        String[] tile = {STT,CODE,NAME,EMAIL,PHONE,AGE};
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < tile.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(tile[i]);
        }
        for (EmployeeDto item: list){
            rowNum ++;
            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(rowNum);
            cell = row.createCell(1);
            cell.setCellValue(item.getCode());
            cell = row.createCell(2);
            cell.setCellValue(item.getName());
            cell= row.createCell(3);
            cell.setCellValue(item.getEmail());
            cell= row.createCell(4);
            cell.setCellValue(item.getPhone());
            cell = row.createCell(5);
            cell.setCellValue(item.getAge());
            cell = row.createCell(6);
            cell.setCellValue(String.valueOf(item.getProvinceDto().getId()));
            cell = row.createCell(7);
            cell.setCellValue(String.valueOf(item.getDistrictDto().getId()));
            cell = row.createCell(8);
            cell.setCellValue(String.valueOf(item.getCommuneDto().getId()));
        }
        try {
            workbook.write(byteAOS);
            workbook.close();
            byteAOS.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new ByteArrayInputStream(byteAOS.toByteArray());
    }
}
