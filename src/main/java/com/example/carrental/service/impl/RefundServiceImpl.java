package com.example.carrental.service.impl;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.dto.RevenueDto;
import com.example.carrental.model.Refund;
import com.example.carrental.model.ReservationStatus;
import com.example.carrental.model.RevenueType;
import com.example.carrental.repository.RefundRepository;
import com.example.carrental.repository.ReservationRepository;
import com.example.carrental.service.RefundService;
import com.example.carrental.service.ReservationService;
import com.example.carrental.service.RevenueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {

    private RefundRepository refundRepository;

    private RevenueService revenueService;
    private ReservationService reservationService;
    private ModelMapper modelMapper;

    public RefundServiceImpl(RefundRepository refundRepository, ModelMapper modelMapper,
                             RevenueService revenueService,
                             ReservationService reservationService) {
        this.refundRepository = refundRepository;
        this.modelMapper = modelMapper;
        this.revenueService = revenueService;
        this.reservationService = reservationService;
    }

    @Override
    public RefundDto addRefund(RefundDto refundDto) {



        if (!isRefundExistsForReservation(refundDto.getReservationId())){


            if(refundDto.getSurcharge() > 0){
                RevenueDto revenueDto = new RevenueDto();
                revenueDto.setRevenueType(RevenueType.SURCHARGE);
                revenueDto.setReservationId(refundDto.getReservationId());
                revenueDto.setTotalRevenue(refundDto.getSurcharge());
                revenueDto.setDate(LocalDate.now());
                revenueService.create(revenueDto);
                reservationService.updateReservationStatus(refundDto.getReservationId(),
                        ReservationStatus.DAMAGED);
            }else if(refundDto.getSurcharge() == 0){
                reservationService.updateReservationStatus(refundDto.getReservationId(),
                        ReservationStatus.COMPLETED);

            }
        }else throw new RuntimeException("Reservation is already refunded");


        Refund refund = modelMapper.map(refundDto,Refund.class);
        refundRepository.save(refund);

        return modelMapper.map(refund,RefundDto.class);
    }
    boolean isRefundExistsForReservation(int reservationId) {
        return refundRepository.findByReservationId(reservationId) != null;
    }

    @Override
    public Refund getRefund(int id) {
        return refundRepository.findById(id).orElse(null);
    }

    @Override
    public List<Refund> getRefunds() {
        return refundRepository.findAll();
    }
}
