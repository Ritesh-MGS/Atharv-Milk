package com.tharv.milk.serviceImpl;

import com.tharv.milk.domain.Collection;
import com.tharv.milk.domain.Farmer;
import com.tharv.milk.dto.CollectionDTO;
import com.tharv.milk.enumeration.MilkType;
import com.tharv.milk.enumeration.Shift;
import com.tharv.milk.mapper.CollectionMapper;
import com.tharv.milk.repository.CollectionRepository;
import com.tharv.milk.repository.FarmerRepository;
import com.tharv.milk.service.CollectionService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;
    private final FarmerRepository farmerRepository;

    public CollectionServiceImpl(CollectionRepository collectionRepository, CollectionMapper collectionMapper, FarmerRepository farmerRepository) {
        this.collectionRepository = collectionRepository;
        this.collectionMapper = collectionMapper;
        this.farmerRepository = farmerRepository;
    }

    @Override
    public CollectionDTO save(CollectionDTO collectionDTO) {
        Collection collection = collectionMapper.collectionDTOToCollection(collectionDTO);
        collection = collectionRepository.save(collection);
        return collectionMapper.collectionToCollectionDTO(collection);
    }

    @Override
    public List<CollectionDTO> findAll() {
        return collectionRepository.findAll().stream()
                .map(collectionMapper::collectionToCollectionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CollectionDTO findOne(int id) {
        return collectionRepository.findById(id)
                .map(collectionMapper::collectionToCollectionDTO)
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        collectionRepository.deleteById(id);
    }

    @Override
    public void uploadCollection(MultipartFile file) throws IOException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Collection> collections = new ArrayList<>();


        try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Assume data is in the first sheet

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);
                if (row == null) continue;
                String dateValue = row.getCell(0).getStringCellValue();
                if ("Total".equalsIgnoreCase(dateValue)) {
                    break; // Stop processing if "Total" is encountered
                }
                // Parse farmer
                int code = Integer.parseInt(row.getCell(1).getStringCellValue()); // Code
                String name = row.getCell(2).getStringCellValue();     // Name
                Farmer farmer = farmerRepository.findById(code)
                        .orElseGet(() -> new Farmer(code, name)); // Create if not exists

                // Parse collection
                Collection collection = new Collection();
                collection.setFarmer(farmer);
                collection.setCollectionDate(LocalDate.parse(dateValue, dateFormatter)); // Date
                collection.setShift(parseShift(row.getCell(3).getStringCellValue()));                             // Shift
                collection.setMilkType(parseMilkType(row.getCell(4).getStringCellValue()));                      // Milk
                collection.setQuantity(new BigDecimal(row.getCell(5).getStringCellValue().trim()));               // Liter
                collection.setFat(new BigDecimal(row.getCell(6).getStringCellValue().trim()));                    // FAT
                collection.setSnf(new BigDecimal(row.getCell(7).getStringCellValue().trim()));                    // SNF

                collections.add(collection);
                farmerRepository.save(farmer); // Save the farmer
            }

            collectionRepository.saveAll(collections); // Save all collections
        }
    }

    private Shift parseShift(String shiftValue) {
        return "M".equalsIgnoreCase(shiftValue) ? Shift.MORNING : Shift.EVENING;
    }

    private MilkType parseMilkType(String milkValue) {
        return "C".equalsIgnoreCase(milkValue) ? MilkType.COW : MilkType.BUFFALO;
    }
}