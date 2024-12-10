package com.tharv.milk.service;

import com.tharv.milk.dto.CentreDTO;
import java.util.List;

public interface CentreService {
    void save(CentreDTO centreDTO);
    List<CentreDTO> findAll();
    CentreDTO findOne(int id);
    void delete(int id);
}