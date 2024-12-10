package com.tharv.milk.service;


import com.tharv.milk.dto.FarmerDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FarmerService {
    FarmerDTO save(FarmerDTO farmerDTO);
    List<FarmerDTO> findAll();
    FarmerDTO findOne(int id);
    void delete(int id);
    void saveFarmersFromXlsx(MultipartFile file);
}