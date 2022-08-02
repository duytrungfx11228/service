package com.globits.da.service;

import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.search.CertificateSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CertificateService {
    // add  certificate
    CertificateDto add(CertificateDto dto);
    // edit  certificate
    CertificateDto edit(UUID id, CertificateDto dto);
    // get one certificate by id
    CertificateDto getById(UUID id);
    // get all certificate
    List<CertificateDto> getAll();
    // delete one certificate
    Boolean delById(UUID id);
    //search by page
    Page<CertificateDto> searchByPage(CertificateSearchDto dto);
    // check certificate
    Boolean checkSameType(CertificateDto dto);

    Boolean checkSameProvince(CertificateDto dto);
}
