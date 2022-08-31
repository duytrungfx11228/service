package com.globits.da.rest;

import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/province")
public class RestProvinceController {
    @Autowired
    private ProvinceService provinceService;
    // save province
    @PostMapping
    public ResponseEntity<ProvinceDto> add(@RequestBody ProvinceDto dto){
        ProvinceDto result = provinceService.add(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // edit province
    @PutMapping("/{id}")
    public ResponseEntity<ProvinceDto> edit(@PathVariable UUID id, @RequestBody ProvinceDto dto){
        ProvinceDto result = provinceService.edit(id, dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // delete province
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProvinc(@PathVariable UUID id){
        return new ResponseEntity<>(provinceService.deletedById(id), HttpStatus.OK);
    }
    // search province
    @PostMapping("/searchByPage")
    public ResponseEntity<Page<ProvinceDto>> search(@RequestBody ProvinceSearchDto searchDto){
        Page<ProvinceDto> result = provinceService.searchByPage(searchDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // get all province
    @GetMapping("/getList")
    public ResponseEntity<List<ProvinceDto>> getList(){
        List<ProvinceDto> result = provinceService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
