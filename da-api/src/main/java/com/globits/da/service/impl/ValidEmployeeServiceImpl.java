package com.globits.da.service.impl;


import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.ValidEmployeeService;
import com.globits.da.utils.Error;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.globits.da.Constants.*;

@Service
public class ValidEmployeeServiceImpl implements ValidEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    // validate employee
    @Override
    public Error validEmployee(EmployeeDto dto, UUID id) {
        Error error = Error.OK;
       if (!validCode(dto, id)){
           error = Error.CODE_ERROR;
       } else if (!validName(dto)){
           error = Error.NAME_ERROR;
       } else if (!validEmail(dto)){
           error = Error.EMAIL_ERROR;
       } else if (!validPhone(dto)){
           error = Error.PHONE_ERROR;
       } else if (!validAge(dto)){
           error = Error.AGE_ERROR;
       }

        return error;
    }
    // validate province District commune
    @Override
    public Error validAddress(Employee etity) {
        Error error = Error.OK;
        if (etity.getProvince() == null){
            error = Error.PROVINCE_ERROR;
        } else if (etity.getDistrict() == null) {
            error = Error.DISTRICT_ERROR;
        } else if (etity.getCommune() == null){
            error = Error.COMMUNE_ERROR;
        } else {
            if (!etity.getDistrict().getId().equals(etity.getCommune().getDistrict().getId())) {
                error = Error.COMMUNE_NOT_BELONG_DISTRICT_INVALID;
            } else if (!etity.getProvince().getId().equals(etity.getDistrict().getProvince().getId())) {
                error = Error.DISTRICT_NOT_BELONG_PROVINCE_INVALID;
            }
        }

        return error;
    }

    // check duplicate code
    private Boolean checkDuplicateCode(EmployeeDto employeeDto,UUID id){
            Long count = employeeRepository.checkCode(employeeDto.getCode(),id);
            if (count > 0){
                return false;
            }
            return true;
    }
    // validate code
    private boolean validCode(EmployeeDto dto , UUID id){
        int lenght = dto.getCode().length();
        if (ObjectUtils.isEmpty(dto.getCode()) || lenght < CODE_MIN_LENGTH || lenght > CODE_MAX_LENGTH ||
            dto.getCode().contains(" ") || !checkDuplicateCode(dto,id)){
            return false;
        }
        return true;
    }
    // validate name
    private boolean validName(EmployeeDto employeeDto){

        if (ObjectUtils.isEmpty(employeeDto.getName())){
            return false;
        }
        return true;
    }
    // validate email
    private boolean validEmail(EmployeeDto dto){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(dto.getEmail());
        if (ObjectUtils.isEmpty(dto.getEmail()) || !matcher.matches()){
            return false;
        }
        return true;
    }
    // validate phone
    private boolean phoneRegex(String phone){
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
       return matcher.matches();
    }
    private boolean validPhone(EmployeeDto dto){
        int lenght = dto.getPhone().length();
        if (!ObjectUtils.isEmpty(dto.getPhone()) && lenght <= PHONE_MAX_LENGTH && lenght >= PHONE_MIN_LENGTH
                && phoneRegex(dto.getPhone())) {
            return true;
        }
        return false;
    }
    // validate age
    private boolean validAge(EmployeeDto dto){
        if (ObjectUtils.isEmpty(dto.getAge()) || dto.getAge() <= 0){
            return false;
        }
        return true;
    }


}
