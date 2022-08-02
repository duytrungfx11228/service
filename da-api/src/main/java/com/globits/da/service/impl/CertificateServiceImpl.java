package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Certificate;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.search.CertificateSearchDto;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.List;

import java.util.UUID;
@Service
public class CertificateServiceImpl extends GenericServiceImpl<Certificate, UUID> implements CertificateService {

    @Autowired
    CertificateRepository repos;
    @Autowired
    ProvinceRepository provinceRepos;
    @Autowired
    EmployeeRepository employeeRepos;
    @Override
    public CertificateDto add(CertificateDto dto) {
        if (dto == null){
            return null;
        }
        Certificate entity = new Certificate();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setDateEfective(dto.getDateEfecttive());
        entity.setDateExpiration(dto.getDateExpiration());
        if (dto.getProvinceDto().getId() != null){
            Province province = provinceRepos.getOne(dto.getProvinceDto().getId());
            if (checkSameProvince(dto)){
                entity.setProvince(province);
            }
        }
        if (dto.getEmployeeDto().getId() != null){
            Employee employee = employeeRepos.getOne(dto.getEmployeeDto().getId());
            if (checkSameType(dto)){
                entity.setEmployee(employee);
            }
        }
        if (entity != null){
            return new CertificateDto(repos.save(entity));
        }
        return null;
    }

    @Override
    public CertificateDto edit(UUID id, CertificateDto dto) {
        if (id == null || dto == null){
            return null;
        }
        Certificate entity = repos.getOne(id);
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setDateEfective(dto.getDateEfecttive());
        entity.setDateExpiration(dto.getDateExpiration());
        if (checkSameType(dto)){
            if (dto.getProvinceDto().getId() != null){
                Province province = provinceRepos.getOne(dto.getProvinceDto().getId());
                entity.setProvince(province);
            }
        }
        if (checkSameType(dto)){
            if (dto.getEmployeeDto().getId() != null){
                Employee employee = employeeRepos.getOne(dto.getEmployeeDto().getId());
                entity.setEmployee(employee);
            }
        }
        if (entity != null){
            return new CertificateDto(repos.save(entity));
        }
        return null;
    }

    @Override
    public CertificateDto getById(UUID id) {
        if (id == null){
            return  null;
        }
        return new CertificateDto(repos.getOne(id));
    }

    @Override
    public List<CertificateDto> getAll() {
        return repos.getAllCertificate();
    }

    @Override
    public Boolean delById(UUID id) {
        if (id != null){
            repos.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<CertificateDto> searchByPage(CertificateSearchDto dto) {
        if (dto == null){
            return null;
        }
        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();
        if (pageIndex > 0){
            pageIndex --;
        } else {
            pageIndex = 0;
        }
        String whereClause = "";
        String orderBy = "ORDER BY entity.createDate DESC";
        String sql = "select new com.globits.da.dto.CertificateDto(entity) from Certificate entity where (1=1)";
        String sqlCount = "select count(entity.id) from Certificate entity where (1=1)";
        if (dto.getKeyWrod() != null && StringUtils.hasText(dto.getKeyWrod())){
            whereClause += "AND ( entity.name LIKE :text or entity.code LIKE :text)";
        }
        sql += whereClause + orderBy;
        sqlCount += whereClause;
        Query q = manager.createQuery(sql, CertificateDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getKeyWrod() != null && StringUtils.hasText(dto.getKeyWrod())) {
            q.setParameter("text", "%" + dto.getKeyWrod() + "%");
            qCount.setParameter("text", "%" + dto.getKeyWrod() + "%");
        }
        int startPosition = pageIndex*pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);

        List<CertificateDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return new PageImpl<>(entities, pageable, count);
    }

    @Override
    public Boolean checkSameType(CertificateDto dto) {
        int count = repos.countCertificateByEmployee(dto.getEmployeeDto().getId(), dto.getType());
        if (count >= 3){
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkSameProvince(CertificateDto dto) {
        int count = repos.countCertificateByProvince(dto.getEmployeeDto().getId(),dto.getProvinceDto().getId(), dto.getType());
        if (count >= 1){
            return false;
        }
        return true;
    }
}
