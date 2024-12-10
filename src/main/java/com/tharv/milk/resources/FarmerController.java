package com.tharv.milk.resources;

import com.tharv.milk.dto.FarmerDTO;
import com.tharv.milk.service.FarmerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping
    public ResponseEntity<FarmerDTO> createFarmer(@RequestBody FarmerDTO farmerDTO) {
        FarmerDTO result = farmerService.save(farmerDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public List<FarmerDTO> getAllFarmers() {
        return farmerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmerDTO> getFarmer(@PathVariable int id) {
        FarmerDTO farmerDTO = farmerService.findOne(id);
        return farmerDTO != null ? ResponseEntity.ok(farmerDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmerDTO> updateFarmer(@PathVariable int id, @RequestBody FarmerDTO farmerDTO) {
        farmerDTO.setId(id);
        FarmerDTO result = farmerService.save(farmerDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable int id) {
        farmerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFarmers(@RequestParam("file") MultipartFile file) {
        try {
            farmerService.saveFarmersFromXlsx(file);
            return ResponseEntity.ok("Farmers successfully uploaded.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the file: " + e.getMessage());
        }
    }
}