package com.globits.da.service;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.search.CommuneSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CommuneService {
    //add commune
    CommuneDto add( CommuneDto dto);
    //edit commune
    CommuneDto edit(UUID id, CommuneDto dto);
    // get one commune by id
    CommuneDto getById(UUID id);
    // get all commune
    List<CommuneDto> getAll();
    // delete one commune
    Boolean delById(UUID id);
    // search by page
    Page<CommuneDto> searchByPage(CommuneSearchDto searchDto);
}
