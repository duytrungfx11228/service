package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import com.globits.da.dto.ProvinceDto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


import java.util.Set;

@Entity
@Table(name = "tbl_province")
@XmlRootElement
public class Province extends BaseObject {
    @Column
    private String code;
    @Column
    private String name;
    @OneToMany(mappedBy = "province",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<District> districtList;
    @OneToMany(mappedBy = "province")
    private Set<Certificate> certificates;

    public Province() {
    }

    public Province(ProvinceDto dto) {
        this.setId(dto.getId());
        this.code = dto.getCode();
        this.name = dto.getName();
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

    public Set<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(Set<District> districtList) {
        this.districtList = districtList;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }


}
