package com.example.carrental.service;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.model.Refund;

import java.util.List;

public interface RefundService {

    RefundDto addRefund(RefundDto refund);

    RefundDto getRefund(int id);

    List<RefundDto> getRefunds();

}
