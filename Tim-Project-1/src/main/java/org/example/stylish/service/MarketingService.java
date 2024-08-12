package org.example.stylish.service;

import lombok.extern.log4j.Log4j2;
import org.example.stylish.dao.campaign.CampaignDao;
import org.example.stylish.dto.ResponseDto;
import org.example.stylish.dto.campaignDto.CreateCampaignDto;
import org.example.stylish.dto.campaignDto.ResponseCampaignDto;
import org.example.stylish.dto.campaignDto.UpdateCampaignDto;
import org.example.stylish.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class MarketingService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    CampaignDao campaignDao;

    private final String key = "campaigns";

    public boolean createCampaign(CreateCampaignDto requestCampaignDto, String pictureUrl) {
        Integer campaignId = campaignDao.insertCampaign(requestCampaignDto, pictureUrl);
        return campaignId != null;
    }

    public ResponseDto<List<ResponseCampaignDto>> getAllCampaigns() {


        List<ResponseCampaignDto> cacheResult = redisUtil.get(key);
        if (cacheResult != null) {
            log.info("data from cache");
            return new ResponseDto<>(cacheResult);
        }

        List<Map<String, Object>> campaigns = campaignDao.getAllCampaigns();
        List<ResponseCampaignDto> responseCampaignDtos = new ArrayList<>();
        for (Map<String, Object> campaign : campaigns) {
            ResponseCampaignDto responseCampaignDto = ResponseCampaignDto.builder()
                .productId((BigInteger) campaign.get("product_id"))
                .story(campaign.get("story").toString())
                .picture(campaign.get("picture").toString())
                .build();
            responseCampaignDtos.add(responseCampaignDto);
        }

        //do nothing if cache connection failed
        redisUtil.set(key, responseCampaignDtos);
        log.info("data from db");

        return new ResponseDto<>(responseCampaignDtos);
    }

    public boolean updateCampaignStory(UpdateCampaignDto updateCampaignDto) {
        if (campaignDao.updateCampaignStory(updateCampaignDto) != 0) {
            //todo: refactor here
            redisUtil.delete(key);
            return true;
        } else {
            return false;
        }
    }

}
