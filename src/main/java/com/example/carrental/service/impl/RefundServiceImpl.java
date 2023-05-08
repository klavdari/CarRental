package com.example.carrental.service.impl;

import com.example.carrental.model.Refund;
import com.example.carrental.repository.RefundRepository;
import com.example.carrental.service.RefundService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {

    private RefundRepository refundRepository;

    public RefundServiceImpl(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }

    @Override
    public Refund addRefund(Refund refund) {
        return refundRepository.save(refund);
    }

    @Override
    public Refund getRefund(int id) {
        return refundRepository.findById(id).orElse(null);
    }

    @Override
    public List<Refund> getRefunds() {
        return refundRepository.findAll();
    }

    @Override
    public Refund updateRefund(Refund refund, int id) {
        Refund newRefund = getRefund(id);

        newRefund.setComments(refund.getComments());
        newRefund.setEmployee(refund.getEmployee());
        newRefund.setReservation(refund.getReservation());
        newRefund.setSurcharge(refund.getSurcharge());

        return refundRepository.save(newRefund);
    }

    @Override
    public void deleteRefund(int id) {
    refundRepository.deleteById(id);
    }
}
