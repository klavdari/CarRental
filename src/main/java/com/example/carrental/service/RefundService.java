package com.example.carrental.service;

import com.example.carrental.model.Refund;

import java.util.List;

public interface RefundService {

    Refund addRefund(Refund refund);

    Refund getRefund(int id);

    List<Refund> getRefunds();

    Refund updateRefund(Refund refund,int id);

    void deleteRefund(int id);
}
