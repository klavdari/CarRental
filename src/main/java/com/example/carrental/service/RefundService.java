package com.example.carrental.service;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.model.Refund;

import java.util.List;

public interface RefundService {

    RefundDto addRefund(RefundDto refund);

    Refund getRefund(int id);

    List<Refund> getRefunds();

}
