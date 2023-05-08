package com.example.carrental.controller;

import com.example.carrental.model.Refund;
import com.example.carrental.service.RefundService;
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
    public ResponseEntity<Refund> addRefund(@RequestBody Refund refund){
        return new ResponseEntity<>(refundService.addRefund(refund), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Refund>> getRefunds(){
        return new ResponseEntity<>(refundService.getRefunds(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refund> getRefund(@PathVariable int id){
        return new ResponseEntity<>(refundService.getRefund(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Refund> updateRefund(@RequestBody Refund refund,int id){
       return new ResponseEntity<>( refundService.updateRefund(refund, id),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRefund(int id){
        refundService.deleteRefund(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



