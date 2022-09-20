package com.globits.da.repository;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, UUID> {
    @Query("SELECT new com.globits.da.dto.CertificateDto(entity) FROM Certificate entity")
    List<CertificateDto> getAllCertificate();

    @Query("SELECT COUNT (ce.id) FROM Certificate ce WHERE ce.employee.id =?1 AND ce.type=?2 AND ce.dateExpiration > CURRENT_DATE ")
    int countCertificateByEmployee(UUID id, String type);

    @Query("SELECT COUNT (ce.id) FROM Certificate ce WHERE ce.employee.id=?1 AND ce.province.id = ?2 AND ce.type = ?3 AND ce.dateExpiration > CURRENT_DATE ")
    int countCertificateByProvince(UUID id, UUID uuid, String type);
}
