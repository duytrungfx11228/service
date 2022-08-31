package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.CommuneDto;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Entity
@Table(name = "tbl_commune")
@XmlRootElement
public class Commune extends BaseObject {
    @Column
    private String code;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn
    private District district;

    public Commune() {
    }

    public Commune(UUID id) {
        this.setId(id);
    }

    public Commune(CommuneDto dto) {
        this.setId(dto.getId());
        this.code = dto.getCode();
        this.name = dto.getName();
        if (dto.getDistrictDto() != null){
            this.district = new District(dto.getDistrictDto());
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
    //endregion
}
