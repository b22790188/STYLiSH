package org.example.stylish.service;

import org.example.stylish.dao.order.OrderDao;
import org.example.stylish.dto.ReportDto.ResponseReportDto;
import org.example.stylish.dto.ResponseDto;
import org.example.stylish.model.WorkProtocol;
import org.example.stylish.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class ReportService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RedisUtil redisUtil;

    public ResponseDto<List<ResponseReportDto>> getUserPaymentReport() {
        List<Map<String, Object>> orders = orderDao.getAllOrders();

        Map<BigInteger, Integer> report = new HashMap<>();

        for (Map<String, Object> order : orders) {
            BigInteger userId = (BigInteger) order.get("user_id");
            Integer total = (Integer) order.get("total");

            report.put(userId, report.getOrDefault(userId, 0) + total);
        }

        List<ResponseReportDto> reportDtos = new ArrayList<>();
        for (Map.Entry<BigInteger, Integer> entry : report.entrySet()) {
            ResponseReportDto reportDto = new ResponseReportDto();
            reportDto.setUserId(entry.getKey());
            reportDto.setTotalPayment(entry.getValue());
            reportDtos.add(reportDto);
        }

        ResponseDto<List<ResponseReportDto>> responseDto = new ResponseDto<>();
        responseDto.setData(reportDtos);
        return responseDto;
    }

    public boolean sendTaskToQueue(WorkProtocol workProtocol) {
        redisUtil.addToStream(workProtocol.getStream(),
            Collections.singletonMap("work", workProtocol.getType()));
        return true;
    }
}
