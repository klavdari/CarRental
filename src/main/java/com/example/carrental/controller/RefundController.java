package com.example.carrental.controller;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.model.Refund;
import com.example.carrental.service.RefundService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/refund")
public class RefundController {

    private RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping
    public ResponseEntity<RefundDto> addRefund(@RequestBody RefundDto refund){
        return new ResponseEntity<>(refundService.addRefund(refund), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RefundDto>> getRefunds(){
        return new ResponseEntity<>(refundService.getRefunds(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDto> getRefund(@PathVariable int id){
        return new ResponseEntity<>(refundService.getRefund(id),HttpStatus.OK);
    }
}



