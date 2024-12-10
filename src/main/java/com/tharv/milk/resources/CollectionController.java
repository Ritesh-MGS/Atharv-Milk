package com.tharv.milk.resources;


import com.tharv.milk.dto.CollectionDTO;
import com.tharv.milk.service.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping
    public ResponseEntity<CollectionDTO> createCollection(@RequestBody CollectionDTO collectionDTO) {
        CollectionDTO result = collectionService.save(collectionDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CollectionDTO> getAllCollections() {
        return collectionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionDTO> getCollection(@PathVariable int id) {
        CollectionDTO collectionDTO = collectionService.findOne(id);
        return collectionDTO != null ? ResponseEntity.ok(collectionDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionDTO> updateCollection(@PathVariable int id, @RequestBody CollectionDTO collectionDTO) {
        collectionDTO.setId(id);
        CollectionDTO result = collectionService.save(collectionDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable int id) {
        collectionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            collectionService.uploadCollection(file);
            return ResponseEntity.ok("File processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        }
    }
}