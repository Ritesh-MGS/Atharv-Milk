package com.tharv.milk.resources;

import com.tharv.milk.dto.CentreDTO;
import com.tharv.milk.service.CentreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centres")
public class CentreController {

    private final CentreService centreService;

    public CentreController(CentreService centreService) {
        this.centreService = centreService;
    }

    @PostMapping
    public ResponseEntity<CentreDTO> createCentre(@RequestBody CentreDTO centreDTO) {
        centreService.save(centreDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<CentreDTO> getAllCentres() {
        return centreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentreDTO> getCentre(@PathVariable int id) {
        CentreDTO centreDTO = centreService.findOne(id);
        return centreDTO != null ? ResponseEntity.ok(centreDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentreDTO> updateCentre(@PathVariable int id, @RequestBody CentreDTO centreDTO) {
        centreDTO.setId(id);
        centreService.save(centreDTO);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentre(@PathVariable int id) {
        centreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}