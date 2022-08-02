package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.search.DistrictSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DistrictService extends GenericService<District, UUID> {
    // save a district
    DistrictDto add(DistrictDto dto);
    // edi a district
    DistrictDto edit(UUID id, DistrictDto dto);
    // get one district by id
    DistrictDto getById(UUID id);
    // get all district
    List<DistrictDto> getAll();
    // delete one district
    Boolean delById(UUID id);
    // search By page
    Page<DistrictDto> searchByPage(DistrictSearchDto dto);
    // get list district by province Id
    List<DistrictDto> getDistrictByProvinceId(UUID provinceId);
}
