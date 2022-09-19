package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Certificate;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProvinceDto extends BaseObjectDto{

    private String code;
    private String name;
    private Set<DistrictDto> setDistrict;
    private Set<CertificateDto> certificateDtoSet;
    public ProvinceDto() {
    }

    public ProvinceDto(UUID id) {
        this.setId(id);
    }

    public ProvinceDto(Province province) {
        this.setId(province.getId());
        this.code = province.getCode();
        this.name = province.getName();
        if (!CollectionUtils.isEmpty(province.getDistrictList())){
            this.setDistrict = new HashSet<>();
            for (District item: province.getDistrictList()) {
                this.setDistrict.add(new DistrictDto(item));
            }
        }
        if (!CollectionUtils.isEmpty(province.getCertificates())){
            this.certificateDtoSet = new HashSet<>();
            for (Certificate item: province.getCertificates()){
                this.certificateDtoSet.add(new CertificateDto(item));
            }
        }
    }
    public ProvinceDto(Province province, boolean bl) {
        this.setId(province.getId());
        this.code = province.getCode();
        this.name = province.getName();
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

    public Set<DistrictDto> getSetDistrict() {
        return setDistrict;
    }

    public void setSetDistrict(Set<DistrictDto> setDistrict) {
        this.setDistrict = setDistrict;
    }

    public Set<CertificateDto> getCertificateDtoSet() {
        return certificateDtoSet;
    }

    public void setCertificateDtoSet(Set<CertificateDto> certificateDtoSet) {
        this.certificateDtoSet = certificateDtoSet;
    }
    //endregion
}
