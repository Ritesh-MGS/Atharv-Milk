package com.tharv.milk.serviceImpl;

import com.tharv.milk.domain.Centre;
import com.tharv.milk.dto.CentreDTO;
import com.tharv.milk.mapper.CentreMapper;
import com.tharv.milk.repository.CentreRepository;
import com.tharv.milk.service.CentreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentreServiceImpl implements CentreService {

    private final CentreRepository centreRepository;
    private final CentreMapper centreMapper;

    public CentreServiceImpl(CentreRepository centreRepository, CentreMapper centreMapper) {
        this.centreRepository = centreRepository;
        this.centreMapper = centreMapper;
    }

    @Override
    public void save(CentreDTO centreDTO) {
        Centre centre = centreMapper.centreDTOToCentre(centreDTO);
        centre = centreRepository.save(centre);
       // return centreMapper.centreToCentreDTO(centre);
    }

    @Override
    public List<CentreDTO> findAll() {
        return centreRepository.findAll().stream()
                .map(centreMapper::centreToCentreDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CentreDTO findOne(int id) {
        return centreRepository.findById(id)
                .map(centreMapper::centreToCentreDTO)
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        centreRepository.deleteById(id);
    }
}