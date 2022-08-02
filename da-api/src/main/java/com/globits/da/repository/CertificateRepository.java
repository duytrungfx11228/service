package com.globits.da.repository;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Repository
public interface CertificateRepository extends JpaRepository<Certificate, UUID> {
    @Query("select new com.globits.da.dto.CertificateDto(entity) from Certificate entity")
    public List<CertificateDto> getAllCertificate();
    // total Certificate by employee same type
    @Query("select count (ce.id) from Certificate ce where ce.employee.id =?1 and ce.type=?2 and ce.dateExpiration > CURRENT_DATE ")
    int countCertificateByEmployee(UUID id, String type);
    // total certificate by province same type
    @Query("select count (ce.id) from Certificate ce where ce.employee.id=?1 and ce.province.id = ?2 and ce.type = ?3 and ce.dateExpiration > CURRENT_DATE ")
    int countCertificateByProvince(UUID id,UUID uuid,String type);
}
