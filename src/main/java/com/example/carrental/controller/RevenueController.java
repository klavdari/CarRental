package com.example.carrental.controller;

import com.example.carrental.dto.RevenueDto;
import com.example.carrental.service.RevenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/revenue")
public class RevenueController {
    private RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping
    public ResponseEntity<List<RevenueDto>> getAll(){
        return new ResponseEntity<>(revenueService.getAll(), HttpStatus.OK);
    }
}
