package com.globits.da.domain;


import com.globits.core.domain.BaseObject;
import com.globits.da.dto.DistrictDto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Entity
@Table(name = "tbl_district")
@XmlRootElement
public class District extends BaseObject {
    @Column
    private String code;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Commune> communes;

    public District() {
    }

    public District(DistrictDto dto) {
        this.setId(dto.getId());
        this.code = dto.getCode();
        this.name = dto.getName();
        if (dto.getProvinceDto() != null){
            this.setProvince(new Province(dto.getProvinceDto()));
        }
    }

    public Set<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(Set<Commune> communes) {
        this.communes = communes;
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
    public Province getProvince() {
        return province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }

}
