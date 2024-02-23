package com.example.carrental.service.impl;

import com.example.carrental.dto.RevenueDto;
import com.example.carrental.model.Revenue;
import com.example.carrental.repository.ReservationRepository;
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

    private ReservationRepository reservationRepository;

    public RevenueServiceImpl(RevenueRepository revenueRepository, ModelMapper modelMapper, ReservationRepository reservationRepository) {
        this.revenueRepository = revenueRepository;
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public RevenueDto create(RevenueDto revenueDto) {

        Revenue revenue = modelMapper.map(revenueDto,Revenue.class);

        revenue.setReservation(reservationRepository.findById(revenueDto.getReservationId()).orElseThrow());
        revenueRepository.save(revenue);
        return modelMapper.map(revenue,RevenueDto.class);
    }

    @Override
    public List<RevenueDto> getAll() {
        return Arrays.asList(modelMapper.map(revenueRepository.findAll(),RevenueDto[].class));
    }

}
