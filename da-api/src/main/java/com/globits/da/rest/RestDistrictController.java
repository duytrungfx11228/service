package com.globits.da.rest;

import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.search.DistrictSearchDto;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/district")
public class RestDistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping
    public ResponseEntity<DistrictDto> add(@RequestBody DistrictDto dto){
        return new ResponseEntity<>(districtService.add(dto), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DistrictDto> edit(@PathVariable UUID id, @RequestBody DistrictDto dto){
        return  new ResponseEntity<>(districtService.edit(id,dto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delById(@PathVariable UUID id){
        return new ResponseEntity<>(districtService.delById(id), HttpStatus.OK);
    }
    @GetMapping("/getList")
    public ResponseEntity<List<DistrictDto>> getList(){
        return new ResponseEntity<>(districtService.getAll(), HttpStatus.OK);
    }
    @PutMapping("/searchByPage")
    public ResponseEntity<Page<DistrictDto>> searchByPage(@RequestBody DistrictSearchDto dto){
        return new ResponseEntity<>(districtService.searchByPage(dto), HttpStatus.OK);
    }
    // get list district by province id
    @GetMapping("/getList/{id}")
    public ResponseEntity<List<DistrictDto>> getListDistrict(@PathVariable UUID id){
        return new ResponseEntity<>(districtService.getDistrictByProvinceId(id), HttpStatus.OK);
    }
}
