package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.search.CommuneSearchDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.CommuneService;
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
public class CommuneServiceImpl extends GenericServiceImpl<Commune, UUID> implements CommuneService {
    @Autowired
    CommuneRepository repos;

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public CommuneDto add(CommuneDto dto) {
        if (dto != null){
            Commune entity = new Commune();
            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            if (dto.getDistrictDto() != null && dto.getDistrictDto().getId() != null){
                entity.setDistrict(districtRepository.getOne(dto.getDistrictDto().getId()));
            }
            return new CommuneDto(repos.save(entity));
        }
        return null;
    }

    @Override
    public CommuneDto edit(UUID id, CommuneDto dto) {
       if (id == null || dto == null){
           return  null;
       }
       Commune entity = repos.getOne(id);
       entity.setName(dto.getName());
       entity.setCode(dto.getCode());
        if (dto.getDistrictDto() != null && dto.getDistrictDto().getId() != null){
            entity.setDistrict(districtRepository.getOne(dto.getDistrictDto().getId()));
        }
        return new CommuneDto(repos.save(entity));
    }

    @Override
    public CommuneDto getById(UUID id) {
        return new CommuneDto(repos.getOne(id));
    }

    @Override
    public List<CommuneDto> getAll() {
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
    public Page<CommuneDto> searchByPage(CommuneSearchDto dto) {

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
        String sqlCount = "select count(entity.id) from  Commune as entity where (1=1)   ";
        String sql = "select new com.globits.da.dto.CommuneDto(entity) from  Commune as entity where (1=1)  ";
        if(dto.getKeyWord() != null && StringUtils.hasText(dto.getKeyWord())){
            whereClause += "AND (entity.name LIKE :text OR entity.code LIKE :text)";
        }
        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, CommuneDto.class);
        Query qCount = manager.createQuery(sqlCount);
        if (dto.getKeyWord() != null && StringUtils.hasText(dto.getKeyWord())){
            q.setParameter("text", "%" + dto.getKeyWord() + "%");
            qCount.setParameter("text", "%" + dto.getKeyWord() + "%");
        }

        int startPosition = pageIndex*pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<CommuneDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return new PageImpl<>(entities,pageable,count);
    }
}
