package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.CertificateDto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "tbl_certificate")
@XmlRootElement
public class Certificate extends BaseObject {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private LocalDateTime dateEfective;
    @Column
    private LocalDateTime dateExpiration;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public Certificate() {
    }

    public Certificate(CertificateDto dto) {
        this.setId(dto.getId());
        this.code = dto.getCode();
        this.name = dto.getName();
        this.type = dto.getType();
        this.dateEfective = dto.getDateEfecttive();
        this.dateExpiration = dto.getDateExpiration();
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

    public LocalDateTime getDateEfective() {
        return dateEfective;
    }

    public void setDateEfective(LocalDateTime dateEfective) {
        this.dateEfective = dateEfective;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
