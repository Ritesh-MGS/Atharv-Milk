package com.tharv.milk.serviceImpl;

import com.tharv.milk.domain.Farmer;
import com.tharv.milk.dto.FarmerDTO;
import com.tharv.milk.mapper.FarmerMapper;
import com.tharv.milk.repository.FarmerRepository;
import com.tharv.milk.service.FarmerService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;
    private final FarmerMapper farmerMapper;

    public FarmerServiceImpl(FarmerRepository farmerRepository, FarmerMapper farmerMapper) {
        this.farmerRepository = farmerRepository;
        this.farmerMapper = farmerMapper;
    }

    @Override
    public FarmerDTO save(FarmerDTO farmerDTO) {
        Farmer farmer = farmerMapper.farmerDTOToFarmer(farmerDTO);
        farmer = farmerRepository.save(farmer);
        return farmerMapper.farmerToFarmerDTO(farmer);
    }

    @Override
    public List<FarmerDTO> findAll() {
        return farmerRepository.findAll().stream()
                .map(farmerMapper::farmerToFarmerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FarmerDTO findOne(int id) {
        return farmerRepository.findById(id)
                .map(farmerMapper::farmerToFarmerDTO)
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        farmerRepository.deleteById(id);
    }

    public void saveFarmersFromXlsx(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Assume the data is in the first sheet
            List<Farmer> farmers = new ArrayList<>();

            // Start reading rows, skipping the header (index 0)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell codeCell = row.getCell(0); // Code
                if (codeCell == null || codeCell.getCellType() != CellType.NUMERIC) {
                    // Skip this row if code is null or not numeric
                    break;
                }

                Farmer farmer = new Farmer();
                farmer.setId((int) codeCell.getNumericCellValue()); // Code
                farmer.setName(row.getCell(1).getStringCellValue()); // English name

                farmers.add(farmer);
            }

            // Save all farmers in batch
            System.out.println("Farmers:"+farmers);
            farmerRepository.saveAll(farmers);

        } catch (IOException e) {
            // Handle exception
            throw new RuntimeException("Error reading XLSX file", e);
        }
    }
}