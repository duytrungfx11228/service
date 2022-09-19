package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;

import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
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
public class ProvinceServiceImpl extends GenericServiceImpl<Province, UUID> implements ProvinceService {
    @Autowired
    private ProvinceRepository repo;

    @Override
    public ProvinceDto add(ProvinceDto dto) {
        if (dto != null) {
            Province entity = new Province();
            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            if (dto.getSetDistrict() != null) {
                Iterator<DistrictDto> iterators = dto.getSetDistrict().iterator();
                Set<District> set = new HashSet<>();
                while (iterators.hasNext()) {
                    DistrictDto districtDto = iterators.next();
                    District district = new District();
                    district.setCode(districtDto.getCode());
                    district.setName(districtDto.getName());
                    district.setProvince(entity);
                    if (districtDto.getCommuneList() != null) {
                        Iterator<CommuneDto> irs = districtDto.getCommuneList().iterator();
                        Set<Commune> communeSet = new HashSet<>();
                        while (irs.hasNext()) {
                            CommuneDto communeDto = irs.next();
                            Commune commune = new Commune();
                            commune.setCode(communeDto.getCode());
                            commune.setName(communeDto.getName());
                            commune.setDistrict(district);
                            communeSet.add(commune);
                        }
                        district.setCommunes(communeSet);
                    }
                    set.add(district);
                }
                entity.setDistrictList(set);
            }

            return new ProvinceDto(repo.save(entity));
        }
        return new ProvinceDto();
    }

    @Override
    public ProvinceDto edit(UUID id, ProvinceDto dto) {
        if (id == null || dto == null) {
            return new ProvinceDto();
        }
        Province entity = repo.getOne(id);
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        if (dto.getSetDistrict() != null) {
            Iterator<DistrictDto> iterators = dto.getSetDistrict().iterator();
            Set<District> set = new HashSet<>();
            while (iterators.hasNext()) {
                DistrictDto districtDto = iterators.next();
                District district = new District();
                district.setCode(districtDto.getCode());
                district.setName(districtDto.getName());
                district.setProvince(entity);
                if (districtDto.getCommuneList() != null) {
                    Iterator<CommuneDto> irs = districtDto.getCommuneList().iterator();

                    Set<Commune> communeSet = new HashSet<>();
                    while (irs.hasNext()) {
                        CommuneDto communeDto = irs.next();
                        Commune commune = new Commune();
                        commune.setCode(communeDto.getCode());
                        commune.setName(communeDto.getName());
                        commune.setDistrict(district);
                        communeSet.add(commune);
                    }
                    if (district.getCommunes() != null) {
                        district.getCommunes().clear();
                        district.getCommunes().addAll(communeSet);
                    } else {
                        district.setCommunes(communeSet);
                    }
                }
                set.add(district);
            }
            if (entity.getDistrictList() != null) {
                entity.getDistrictList().clear();
                entity.getDistrictList().addAll(set);
            } else {
                entity.setDistrictList(set);
            }

        }
        return new ProvinceDto(repo.save(entity));
    }

    @Override
    public ProvinceDto getById(UUID id) {
        return new ProvinceDto(repo.getOne(id));
    }

    @Override
    public List<ProvinceDto> getAll() {
        return repo.getAllProvince();
    }

    @Override
    public boolean deletedById(UUID id) {
        if (id != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<ProvinceDto> searchByPage(ProvinceSearchDto dto) {
        if (dto == null) {
            return null;
        }
        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();
        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
            pageSize = 10;
        }
        String whereClause = "";
        String orderBy = "ORDER BY entity.createDate DESC";
        String sql = "select new com.globits.da.dto.ProvinceDto(entity) from Province entity where 1=1 ";
        String sqlCount = "select count(entity.id) from Province as entity where 1=1";
        if (dto.getKeyWord() != null && StringUtils.hasText(dto.getKeyWord())) {
            whereClause += "AND (entity.name like:text OR entity.code like:text)";
        }
        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, ProvinceDto.class);
        Query qcount = manager.createQuery(sqlCount);

        if (dto.getKeyWord() != null && StringUtils.hasText(dto.getKeyWord())) {
            q.setParameter("text", "%" + dto.getKeyWord() + "%");
            qcount.setParameter("text", "%" + dto.getKeyWord() + "%");
        }
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<ProvinceDto> dtoList = q.getResultList();
        long count = (long) qcount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return new PageImpl<>(dtoList, pageable, count);
    }
}
