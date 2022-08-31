package com.globits.da.rest;

import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.search.CertificateSearchDto;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificates")
public class RestCertificateController {
    @Autowired
    private CertificateService certificateService;
    @PostMapping
    public ResponseEntity<CertificateDto> add(@RequestBody CertificateDto dto){
        return new ResponseEntity<>(certificateService.add(dto), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CertificateDto> edit(@PathVariable UUID id, @RequestBody CertificateDto dto){
        return new ResponseEntity<>(certificateService.edit(id,dto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delById(@PathVariable UUID id){
        return new ResponseEntity<>(certificateService.delById(id), HttpStatus.OK) ;
    }
    @GetMapping("/getList")
    public ResponseEntity<List<CertificateDto>> getALLCertificate(){
        return new ResponseEntity<>(certificateService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/searchByPage")
    public ResponseEntity<Page<CertificateDto>> searchByPage( @RequestBody CertificateSearchDto dto){
        return new ResponseEntity<>(certificateService.searchByPage(dto), HttpStatus.OK);
    }
}
