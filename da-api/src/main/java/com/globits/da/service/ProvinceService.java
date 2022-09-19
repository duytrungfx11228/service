package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProvinceService extends GenericService<Province, UUID> {

    ProvinceDto add(ProvinceDto dto);

    ProvinceDto edit(UUID id, ProvinceDto dto);

    ProvinceDto getById(UUID id);

    List<ProvinceDto> getAll();

    boolean deletedById(UUID id);

    Page<ProvinceDto> searchByPage(ProvinceSearchDto dto);
}
