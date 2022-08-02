package com.globits.da.rest;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.search.CommuneSearchDto;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/communes")
public class RestCommuneController {
    @Autowired
    CommuneService communeService;

    @PostMapping
    public ResponseEntity<CommuneDto> add(@RequestBody CommuneDto dto){
        return new ResponseEntity<>(communeService.add(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommuneDto> edit(@PathVariable UUID id, @RequestBody CommuneDto dto){
        return new ResponseEntity<>(communeService.edit(id,dto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delById(@PathVariable UUID id){
        return  new ResponseEntity<>(communeService.delById(id), HttpStatus.OK);
    }
    @GetMapping("/getList")
    public ResponseEntity<List<CommuneDto>> getAll(){
        return new ResponseEntity<>(communeService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/searchByPage")
    public ResponseEntity<Page<CommuneDto>> searchByPage(CommuneSearchDto dto){
        return new ResponseEntity<>(communeService.searchByPage(dto), HttpStatus.OK);
    }
}
