package com.example.carrental.service;

import com.example.carrental.dto.RevenueDto;

import java.util.List;

public interface RevenueService {

    RevenueDto create(RevenueDto revenueDto);

    List<RevenueDto> getAll();

}
