package com.globits.da.service;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.search.CommuneSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CommuneService {

    CommuneDto add(CommuneDto dto);

    CommuneDto edit(UUID id, CommuneDto dto);

    List<CommuneDto> getAll();

    boolean delById(UUID id);

    Page<CommuneDto> searchByPage(CommuneSearchDto searchDto);

    CommuneDto getById(UUID id);
}
