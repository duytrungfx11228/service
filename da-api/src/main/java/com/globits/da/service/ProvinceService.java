package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProvinceService extends GenericService<Province, UUID> {
    // save  a province
    ProvinceDto add(ProvinceDto dto);
    // edit a province
    ProvinceDto edit(UUID id, ProvinceDto dto);
    // get one province by id
    ProvinceDto getById(UUID id);

    // get all province
    List<ProvinceDto> getAll();
    // delete province
    Boolean deletedById(UUID id);
    // search province
    Page<ProvinceDto> searchByPage(ProvinceSearchDto dto);
}
