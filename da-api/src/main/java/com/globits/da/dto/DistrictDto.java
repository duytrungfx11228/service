package com.globits.da.dto;


import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class DistrictDto extends BaseObjectDto {
    private String code;
    private String name;
    private ProvinceDto provinceDto;
    private Set<CommuneDto> communeList;

    public DistrictDto() {
    }

    public DistrictDto(UUID id) {
        this.setId(id);
    }

    public DistrictDto(District district , boolean bl) {
        this.setId(district.getId());
        this.code = district.getCode();
        this.name = district.getName();
        if (district.getProvince() != null){
            this.provinceDto = new ProvinceDto(district.getProvince(), true);
        }
    }
    public DistrictDto(District district) {
        this.setId(district.getId());
        this.code = district.getCode();
        this.name = district.getName();
        if (district.getProvince() != null){
            this.provinceDto = new ProvinceDto(district.getProvince(), true);
        }
        if (district.getCommunes() != null && district.getCommunes().size() > 0){
            Set<CommuneDto> set = new HashSet<>();
            for (Commune item: district.getCommunes()) {
                CommuneDto dto = new CommuneDto(item);
                set.add(dto);
            }
            this.communeList = set;
        }
    }

    public ProvinceDto getProvinceDto() {
        return provinceDto;
    }
    public void setProvinceDto(ProvinceDto provinceDto) {
        this.provinceDto = provinceDto;
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

    public Set<CommuneDto> getCommuneList() {
        return communeList;
    }

    public void setCommuneList(Set<CommuneDto> communeList) {
        this.communeList = communeList;
    }
}
