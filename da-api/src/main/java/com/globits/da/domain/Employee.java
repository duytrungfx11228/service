package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.EmployeeDto;


import javax.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "tbl_employee")
@XmlRootElement

public class Employee extends BaseObject {

    //@NotNull
    //@Lenght(min = 6,max = 10)
    @Column
    private String code;
    //@NotNull
    @Column
    private String name;
    //@Email
    //@NotNull
    @Column
    private String email;
    //@NotNull
    //@Lenght(min = 10, max = 11)
    @Column
    private String phone;
    //@Min(value = 0)
    @Column
    private int age;
    //@NotNull
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    //@NotNull
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
    //@NotNull
    @ManyToOne
    @JoinColumn(name = "commune_id")
    private Commune commune;

    public Employee() {
    }

    public Employee(EmployeeDto dto) {
        this.code = dto.getCode();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.age = dto.getAge();
        if (dto.getProvinceDto() != null){
            this.province = new Province(dto.getProvinceDto());
        }
        if (dto.getDistrictDto() != null){
            this.district = new District(dto.getDistrictDto());
        }
        if (dto.getDistrictDto() != null){
            this.commune = new Commune(dto.getCommuneDto());
        }

    }

    @OneToMany(mappedBy = "employee")

    private List<Certificate> certificateList;


    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
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

    public List<Certificate> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<Certificate> certificateList) {
        this.certificateList = certificateList;
    }
}
