package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.search.DistrictSearchDto;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.persistence.Query;
import java.util.*;

@Service
public class DistrictServiceImpl extends GenericServiceImpl<District, UUID> implements DistrictService {
    @Autowired
    DistrictRepository repos;
    @Autowired
    ProvinceRepository provinceRepository;
    @Override
    public DistrictDto add(DistrictDto dto) {
        District entity = new District();
        if (dto != null) {
            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            if (dto.getProvinceDto() != null && dto.getProvinceDto().getId() != null) {
                entity.setProvince(provinceRepository.getOne(dto.getProvinceDto().getId()));
            }
            if (dto.getCommuneList() != null){
                Iterator<CommuneDto> iterators = dto.getCommuneList().iterator();
                Set<Commune> setCommune = new HashSet<>();
                while (iterators.hasNext()){
                    CommuneDto communeDto = iterators.next();
                    Commune commune = new Commune();
                    commune.setCode(communeDto.getCode());
                    commune.setName(commune.getName());
                    commune.setDistrict(entity);
                    setCommune.add(commune);
                }
                entity.setCommunes(setCommune);
            }
            entity = repos.save(entity);
            return new DistrictDto(entity);
        }
       return null;
    }

    @Override
    public DistrictDto edit(UUID id, DistrictDto dto) {
        if(id == null || dto == null){
           return null;
        }
        District entity = repos.getOne(id);
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        if (dto.getProvinceDto() != null && dto.getProvinceDto().getId() != null) {
            entity.setProvince(provinceRepository.getOne(dto.getProvinceDto().getId()));
        }
        if (dto.getCommuneList() != null){
            Iterator<CommuneDto> iterators = dto.getCommuneList().iterator();
            Set<Commune> setCommune = new HashSet<>();
            while (iterators.hasNext()){
                CommuneDto communeDto = iterators.next();
                Commune commune = new Commune();
                commune.setCode(communeDto.getCode());
                commune.setName(commune.getName());
                commune.setDistrict(entity);
                setCommune.add(commune);
            }
            if (entity.getCommunes() != null){
                entity.getCommunes().clear();
                entity.getCommunes().addAll(setCommune);
            } else {
                entity.setCommunes(setCommune);
            }
        }
        return new DistrictDto(repos.save(entity));
    }

    @Override
    public DistrictDto getById(UUID id) {
        if (id == null){
            return null;
        }

        return new DistrictDto(repos.getOne(id));
    }

    @Override
    public List<DistrictDto> getAll() {
        return repos.getList();
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
    public Page<DistrictDto> searchByPage(DistrictSearchDto dto) {
        if (dto == null){
            return null;
        }
        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();
        if(pageIndex > 0){
            pageIndex --;
        } else {
            pageIndex = 0;
        }
        String whereClause= "";
        String orderBy = "ORDER BY entity.createDate DESC";
        String sql = "select new com.globits.da.dto.DistrictDto(entity) from  District as entity where (1=1) ";
        String sqlCount = "select count(entity.id) from  District as entity where (1=1)";
        if (dto.getKeyWord() != null && StringUtils.hasText(dto.getKeyWord())){
            whereClause += "AND (entity.name LIKE :text OR entity.code LIKE :text)";
        }
        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, DistrictDto.class);
        Query qCount = manager.createQuery(sqlCount);
        if (dto.getKeyWord() != null && StringUtils.hasText(dto.getKeyWord())){
            q.setParameter("text", "%" + dto.getKeyWord() + "%");
            qCount.setParameter("text", "%" + dto.getKeyWord() + "%");
        }
        int startPosition = pageIndex*pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<DistrictDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex,pageSize);

        return new PageImpl<>(entities,pageable,count);
    }

    @Override
    public List<DistrictDto> getDistrictByProvinceId(UUID provinceId) {
        return repos.getDistrictByProvinceId(provinceId);
    }
}
