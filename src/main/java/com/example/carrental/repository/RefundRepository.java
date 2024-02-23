package com.example.carrental.repository;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Ref;

public interface RefundRepository extends JpaRepository<Refund,Integer> {

    Refund findByReservationId(int id);
}
