package org.example.stylish.dao.campaign;

import jakarta.annotation.Nullable;
import org.example.stylish.dto.campaignDto.CreateCampaignDto;
import org.example.stylish.dto.campaignDto.UpdateCampaignDto;

import java.util.List;
import java.util.Map;

public interface CampaignDao {
    @Nullable
    Integer insertCampaign(CreateCampaignDto createCampaignDto, String pictureUrl);

    List<Map<String, Object>> getAllCampaigns();

    Integer updateCampaignStory(UpdateCampaignDto updateCampaignDto);
}
