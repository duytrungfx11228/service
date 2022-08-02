package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Commune;

import java.util.UUID;

public class CommuneDto extends BaseObjectDto {
    private String code;
    private String name;
    private DistrictDto districtDto;

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

    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }

    public CommuneDto() {
    }

    public CommuneDto(UUID id) {
        this.setId(id);
    }

    public CommuneDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public CommuneDto(Commune commune) {
        if (commune != null){
            this.setId(commune.getId());
            this.code = commune.getCode();
            this.name = commune.getName();
            if (commune.getDistrict() != null ){
                this.districtDto = new DistrictDto(commune.getDistrict(),true);
            }
        }

    }
}
