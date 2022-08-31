package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Certificate;
import com.globits.da.domain.Employee;

import java.util.HashSet;
import java.util.Set;

public class EmployeeDto extends BaseObjectDto {
    private String code;
    private String name;
    private String email;
    private String phone;
    private int age;
    private ProvinceDto provinceDto;
    private DistrictDto districtDto;
    private CommuneDto communeDto;
    private Set<CertificateDto> certificateDtos;
    public EmployeeDto() {

    }

    public EmployeeDto(Employee entity) {
            this.setId(entity.getId());
            this.code = entity.getCode();
            this.name = entity.getName();
            this.email = entity.getEmail();
            this.phone = entity.getPhone();
            this.age = entity.getAge();
            if (entity.getProvince() != null){
                this.provinceDto = new ProvinceDto(entity.getProvince());
            }
            if (entity.getDistrict() != null){
                this.districtDto = new DistrictDto(entity.getDistrict());
            }
            if (entity.getCommune() != null){
                this.communeDto = new CommuneDto(entity.getCommune());
            }
            if (entity.getCertificateList() != null){
                Set<CertificateDto> set = new HashSet<>();
                for (Certificate item: entity.getCertificateList()) {
                    CertificateDto dto = new CertificateDto(item);
                    set.add(dto);
                }
                this.setCertificateDtos(set);
            }
    }
    //region getter-setter
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProvinceDto getProvinceDto() {
        return provinceDto;
    }

    public void setProvinceDto(ProvinceDto provinceDto) {
        this.provinceDto = provinceDto;
    }

    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }

    public CommuneDto getCommuneDto() {
        return communeDto;
    }

    public void setCommuneDto(CommuneDto communeDto) {
        this.communeDto = communeDto;
    }

    public Set<CertificateDto> getCertificateDtos() {
        return certificateDtos;
    }

    public void setCertificateDtos(Set<CertificateDto> certificateDtos) {
        this.certificateDtos = certificateDtos;
    }
    //endregion
}
