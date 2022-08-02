package com.globits.da.repository;

import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {
    @Query("select new com.globits.da.dto.DistrictDto(entity) from District entity")
    List<DistrictDto> getList();

    @Query("select new com.globits.da.dto.DistrictDto(d) from District d where d.province.id =?1")
    List<DistrictDto> getDistrictByProvinceId(UUID provinceId);
}
