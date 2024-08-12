package org.example.stylish.controller;

import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.ResponseDto;
import org.example.stylish.dto.campaignDto.ResponseCampaignDto;
import org.example.stylish.dto.campaignDto.UpdateCampaignDto;
import org.example.stylish.service.MarketingService;
import org.example.stylish.utils.RedisRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/1.0/marketing")
public class MarkertingController {

    @Autowired
    private RedisRateLimiter redisRateLimiter;

    @Autowired
    MarketingService marketingService;

    @GetMapping("/campaigns")
    public ResponseEntity<?> getAllCampaigns() {
        ResponseDto<List<ResponseCampaignDto>> responseDto = marketingService.getAllCampaigns();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/campaigns")
    public ResponseEntity<?> updateCampaign(@RequestBody UpdateCampaignDto updateCampaignDto) {
        marketingService.updateCampaignStory(updateCampaignDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
