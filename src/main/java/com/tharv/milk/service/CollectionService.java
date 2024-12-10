package com.tharv.milk.service;


import com.tharv.milk.dto.CollectionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CollectionService {
    CollectionDTO save(CollectionDTO collectionDTO);
    List<CollectionDTO> findAll();
    CollectionDTO findOne(int id);
    void delete(int id);
    void uploadCollection(MultipartFile file) throws IOException;
}