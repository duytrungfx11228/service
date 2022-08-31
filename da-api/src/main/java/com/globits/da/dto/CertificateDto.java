package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Certificate;

import java.time.LocalDateTime;


public class CertificateDto extends BaseObjectDto {
    private String code;
    private String name;
    private String type;
    private LocalDateTime dateRange;
    private LocalDateTime dateExpiration;
    private ProvinceDto provinceDto;
    private EmployeeDto employeeDto;

    public CertificateDto(Certificate entity) {
            this.setId(entity.getId());
            this.code = entity.getCode();
            this.name = entity.getName();
            this.type = entity.getType();
            this.dateRange = entity.getDateRange();
            this.dateExpiration = entity.getDateExpiration();
            if (entity.getEmployee() != null){
                this.employeeDto = new EmployeeDto(entity.getEmployee());
            }
            if (entity.getProvince() != null){
                this.provinceDto = new ProvinceDto(entity.getProvince());
            }
    }

    //region getter-setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateRange() {
        return dateRange;
    }

    public void setDateRange(LocalDateTime dateRange) {
        this.dateRange = dateRange;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public ProvinceDto getProvinceDto() {
        return provinceDto;
    }

    public void setProvinceDto(ProvinceDto provinceDto) {
        this.provinceDto = provinceDto;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
    //endregion


}
