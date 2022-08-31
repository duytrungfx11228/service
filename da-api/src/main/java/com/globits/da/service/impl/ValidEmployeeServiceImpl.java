package com.globits.da.service.impl;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.CommuneService;
import com.globits.da.service.DistrictService;
import com.globits.da.service.ValidEmployeeService;
import com.globits.da.utils.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.globits.da.Constants.*;

@Service
public class ValidEmployeeServiceImpl implements ValidEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CommuneService communeService;
    @Autowired
    private DistrictService districtService;
    // validate employee
    @Override
    public Error validEmployee(EmployeeDto dto) {
        Error error ;
        if(dto == null){
            error = Error.DTO_ERROR;
        } else if (!validCode(dto)){
            error = Error.CODE_ERROR;
        } else if (!validName(dto)){
            error = Error.NAME_ERROR;
        } else if (!validEmail(dto)){
            error = Error.EMAIL_ERROR;
        } else if (!validPhone(dto)){
            error = Error.PHONE_ERROR;
        } else if (!validAge(dto)) {
            error = Error.AGE_ERROR;
        } else {
            error = validAddress(dto);
        }
        return error;
    }
    // validate province District commune
    @Override
    public Error validAddress(EmployeeDto dto) {
        Error error = Error.OK;
        if (dto.getProvinceDto() == null) {
            error = Error.PROVINCE_ERROR;
        } else if (dto.getDistrictDto() == null) {
            error = Error.DISTRICT_ERROR;
        } else if (dto.getCommuneDto() == null) {
            error = Error.COMMUNE_ERROR;
        } else {
            DistrictDto districtDto = districtService.getById(dto.getDistrictDto().getId());
            CommuneDto communeDto = communeService.getById(dto.getCommuneDto().getId());
            if (!dto.getProvinceDto().getId().equals(districtDto.getProvinceDto().getId())) {
                error = Error.DISTRICT_NOT_BELONG_PROVINCE_INVALID;
            }
            if (!dto.getDistrictDto().getId().equals(communeDto.getDistrictDto().getId())) {
                error = Error.COMMUNE_NOT_BELONG_DISTRICT_INVALID;
            }
        }
        return error;
    }

    // check duplicate code
    private boolean checkDuplicateCode(EmployeeDto employeeDto){
            long count = employeeRepository.countAllByCode(employeeDto.getCode());
        return count <= 0;
    }
    // validate code
    private boolean validCode(EmployeeDto dto ){
        int leng = dto.getCode().length();
        return !ObjectUtils.isEmpty(dto.getCode()) && leng >= CODE_MIN_LENGTH && leng <= CODE_MAX_LENGTH &&
                !dto.getCode().contains(" ") && checkDuplicateCode(dto);
    }
    // validate name
    private boolean validName(EmployeeDto employeeDto){

        return !ObjectUtils.isEmpty(employeeDto.getName());
    }
    // validate email
    private boolean validEmail(EmployeeDto dto){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(dto.getEmail());
        return !ObjectUtils.isEmpty(dto.getEmail()) && matcher.matches();
    }
    // validate phone
    private boolean phoneRegex(String phone){
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
       return matcher.matches();
    }
    private boolean validPhone(EmployeeDto dto){
        int lenght = dto.getPhone().length();
        return !ObjectUtils.isEmpty(dto.getPhone()) && lenght <= PHONE_MAX_LENGTH && lenght >= PHONE_MIN_LENGTH
                && phoneRegex(dto.getPhone());
    }
    // validate age
    private boolean validAge(EmployeeDto dto){
        return !ObjectUtils.isEmpty(dto.getAge()) && dto.getAge() > 0;
    }

}
