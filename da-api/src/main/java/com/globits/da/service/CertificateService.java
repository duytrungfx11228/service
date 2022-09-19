package com.globits.da.service;

import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.search.CertificateSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CertificateService {

    CertificateDto add(CertificateDto dto);

    CertificateDto edit(UUID id, CertificateDto dto);

    List<CertificateDto> getAll();

    boolean delById(UUID id);

    Page<CertificateDto> searchByPage(CertificateSearchDto dto);

    boolean checkSameType(CertificateDto dto);

    boolean checkSameProvince(CertificateDto dto);
}
