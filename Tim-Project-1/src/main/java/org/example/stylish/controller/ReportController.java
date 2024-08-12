package org.example.stylish.controller;

import org.example.stylish.dto.ReportDto.ResponseReportDto;
import org.example.stylish.dto.ResponseDto;
import org.example.stylish.model.WorkProtocol;
import org.example.stylish.service.ReportService;
import org.example.stylish.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ReportService reportService;

    @GetMapping("/api/1.0/report/payment")
    public ResponseEntity<?> getPaymentReportByWebserver() {
        try {
            ResponseDto<List<ResponseReportDto>> responseDto = reportService.getUserPaymentReport();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/2.0/report/payment")
    public ResponseEntity<?> getPaymentReportByWorker(@RequestBody WorkProtocol workProtocol) {
        try {
            reportService.sendTaskToQueue(workProtocol);
            return new ResponseEntity<>("Please wait, report will be delivered by email later", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
