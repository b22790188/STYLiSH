package org.example.stylish.dao.campaign;

import jakarta.annotation.Nullable;
import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.campaignDto.CreateCampaignDto;
import org.example.stylish.dto.campaignDto.UpdateCampaignDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository
public class CamaignDaoImpl implements CampaignDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Nullable
    public Integer insertCampaign(CreateCampaignDto createCampaignDto, String pictureUrl) {
        try {
            String sql = "INSERT INTO campaign (`product_id`, `story`, `picture`) VALUES (?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            BigDecimal productId = new BigDecimal(createCampaignDto.getProductId());
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setBigDecimal(1, productId);
                ps.setString(2, createCampaignDto.getStory());
                ps.setString(3, pictureUrl);
                return ps;
            }, keyHolder);
            return keyHolder.getKey().intValue();
        } catch (NullPointerException npe) {
            return null;
        }
    }

    public List<Map<String, Object>> getAllCampaigns() {
        try {
            String sql = "SELECT * FROM campaign";
            return jdbcTemplate.queryForList(sql);
        } catch (DataAccessException dae) {
            log.error(dae);
            return Collections.emptyList();
        }
    }

    public Integer updateCampaignStory(UpdateCampaignDto updateCampaignDto) {
        try {
            String sql = "UPDATE campaign SET story = ? WHERE product_id = ?";
            BigDecimal productId = new BigDecimal(updateCampaignDto.getProductId());
            String story = updateCampaignDto.getStory();
            return jdbcTemplate.update(sql, story, productId);
        } catch (DataAccessException dae) {
            log.error(dae);
            return 0;
        }
    }
}
