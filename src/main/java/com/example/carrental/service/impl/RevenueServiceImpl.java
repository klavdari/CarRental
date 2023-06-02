package com.example.carrental.service.impl;

import com.example.carrental.dto.RevenueDto;
import com.example.carrental.model.Revenue;
import com.example.carrental.repository.RevenueRepository;
import com.example.carrental.service.RevenueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    private RevenueRepository revenueRepository;
    private ModelMapper modelMapper;

    public RevenueServiceImpl(RevenueRepository revenueRepository, ModelMapper modelMapper) {
        this.revenueRepository = revenueRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RevenueDto create(RevenueDto revenueDto) {

        Revenue revenue = modelMapper.map(revenueDto,Revenue.class);

        revenueRepository.save(revenue);
        return modelMapper.map(revenue,RevenueDto.class);
    }

    @Override
    public List<RevenueDto> getAll() {
        return Arrays.asList(modelMapper.map(revenueRepository.findAll(),RevenueDto[].class));
    }

}
