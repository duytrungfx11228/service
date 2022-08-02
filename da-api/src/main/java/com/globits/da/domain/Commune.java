package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tbl_commune")
@XmlRootElement
public class Commune extends BaseObject {
    @Column
    private String code;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "district_id",nullable = false)
    private District district;

    public Commune() {
    }

    public Commune(CommuneDto dto) {
        this.setId(dto.getId());
        this.code = dto.getCode();
        this.name = dto.getName();
        if (dto.getDistrictDto() != null){
            this.district = new District(dto.getDistrictDto());
        }
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
