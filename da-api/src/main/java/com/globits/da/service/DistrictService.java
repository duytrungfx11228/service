package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.search.DistrictSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DistrictService extends GenericService<District, UUID> {

    DistrictDto add(DistrictDto dto);

    DistrictDto edit(UUID id, DistrictDto dto);

    DistrictDto getById(UUID id);

    List<DistrictDto> getAll();

    boolean delById(UUID id);

    Page<DistrictDto> searchByPage(DistrictSearchDto dto);

    List<DistrictDto> getDistrictByProvinceId(UUID provinceId);
}
